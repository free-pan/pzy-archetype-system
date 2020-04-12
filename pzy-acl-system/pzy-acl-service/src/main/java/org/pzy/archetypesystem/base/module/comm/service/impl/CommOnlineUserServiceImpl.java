package org.pzy.archetypesystem.base.module.comm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.comm.dao.CommOnlineUserDAO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommOnlineUserSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommOnlineUser;
import org.pzy.archetypesystem.base.module.comm.mapstruct.CommOnlineUserMapStruct;
import org.pzy.archetypesystem.base.module.comm.service.CommOnlineUserService;
import org.pzy.archetypesystem.base.module.comm.vo.CommOnlineUserVO;
import org.pzy.opensource.comm.exception.ValidateException;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.mybatisplus.util.PageUtil;
import org.pzy.opensource.redis.support.springboot.annotation.LockBuilder;
import org.pzy.opensource.redis.support.springboot.annotation.WinterLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * comm_online_user 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-06
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = CommOnlineUserServiceImpl.CACHE_NAME)
public class CommOnlineUserServiceImpl extends ServiceTemplate<CommOnlineUserDAO, CommOnlineUser> implements CommOnlineUserService {

    public static final String CACHE_NAME = "CommOnlineUserServiceImpl";

    @Autowired
    private CommOnlineUserMapStruct mapStruct;

    /**
     * 使用一种便捷的方法来查找特定用户的所有会话.
     * 通过确保FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME使用用户名填充名称为的session属性来完成此操作。由于Spring Session不知道所使用的身份验证机制，因此您有责任确保填充属性
     */
    @Autowired
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;

    private final String LOGIN_USER_DATA_RECORD_PREFIX = "LOGIN_USER_DATA_RECORD_PREFIX";

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void clearCache() {
        if (log.isDebugEnabled()) {
            log.debug("清除[{}]服务类缓存!", this.getClass().getName());
        }
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public PageT<CommOnlineUserVO> pageAndCache(CommOnlineUserSearchDTO dto) {
        if (null == dto) {
            return PageT.EMPTY();
        }
        // 查询关键词去空格
        CommOnlineUserSearchDTO condition = mapStruct.searchDtoToSearchDTO(dto);
        // 系统的分页条件转换为mybatis plus的分页条件
        IPage<CommOnlineUser> mybatisPlusPageCondition = toMybatisPlusPage(condition.getPg());
        // 构建mybatis plus查询条件
        QueryWrapper<CommOnlineUser> queryWrapper = buildQueryWrapper();
        // mybatis plus分页查询
        IPage<CommOnlineUser> mybatisPlusPageResult = super.page(mybatisPlusPageCondition, queryWrapper);
        // mybatis plus分页结果, 转系统分页结果
        List<CommOnlineUserVO> voList = this.mapStruct.entityToDTO(mybatisPlusPageResult.getRecords());
        return PageUtil.mybatisPlusPage2PageT(mybatisPlusPageResult, voList);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @WinterLock(lockBuilder = @LockBuilder(prefix = LOGIN_USER_DATA_RECORD_PREFIX, key = "[0].userId"))
    @Override
    public Long saveAndClearCache(@Valid @NotNull CommOnlineUserAddDTO dto) {
        // 获取当前用户登录的session数量(key为sessionid)
        Map<String, Session> sessionMap = findByIndexNameSessionRepository.findByPrincipalName(String.valueOf(dto.getUserId()));
        if (sessionMap.size() >= dto.getSingleUserMaxSession()) {
            // 当前用户创建的会话数量,超过允许创建的最大会话数量
            if (dto.getKickoutAfter()) {
                // 踢出后登录的
                if (log.isWarnEnabled()) {
                    log.warn("用户id[{}]的会话数已达[{}]个,超过系统允许的单个用户最大会话数[{}],强制shiro进行登出!", dto.getUserId(), sessionMap.size(), dto.getSingleUserMaxSession());
                }
                throw new ValidateException("您的账号的会话数量已超过系统允许的最大数量,登录失败!");
            } else {
                // 踢出先登录的
                Collection<Session> sessionLiset = sessionMap.values();
                Session session = findMinCreationTimeSession(sessionLiset);
                if (log.isDebugEnabled()) {
                    log.debug("当前用户超过最大并发数,踢出最先登录的: " + session.getId());
                }
                session.setAttribute(GlobalConstant.MAX_SESSION_FORCE_LOGOUT_KEY, true);
                findByIndexNameSessionRepository.save(session);
            }
        }
        // 对象转换
        CommOnlineUser entity = mapStruct.addSourceToEntity(dto);
        // 持久化
        boolean optSuc = super.save(entity);
        return entity.getId();
    }

    /**
     * 找出创建时间最小的Session
     *
     * @param sessionLiset
     * @return
     */
    private Session findMinCreationTimeSession(Collection<Session> sessionLiset) {
        Session minCreationTimeSession = null;
        for (Session session : sessionLiset) {
            if (minCreationTimeSession == null) {
                minCreationTimeSession = session;
            } else if (session.getCreationTime().compareTo(minCreationTimeSession.getCreationTime()) < 0) {
                // session的创建时间小于minCreationTimeSession的时间,则当前session设置为minCreationTimeSession
                minCreationTimeSession = session;
            }
        }
        return minCreationTimeSession;
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public CommOnlineUserVO getByIdAndCache(Serializable id) {
        if (null == id) {
            return null;
        }
        CommOnlineUser entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public void deleteBySessionIdAndClearCache(String sessionId) {
        this.remove(super.buildQueryWrapper().eq(CommOnlineUser.SESSION_ID, sessionId));
    }
}
