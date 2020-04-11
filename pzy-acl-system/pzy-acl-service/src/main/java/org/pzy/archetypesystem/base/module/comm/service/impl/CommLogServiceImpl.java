package org.pzy.archetypesystem.base.module.comm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.comm.dao.CommLogDAO;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogAddDTO;
import org.pzy.archetypesystem.base.module.comm.dto.CommLogSearchDTO;
import org.pzy.archetypesystem.base.module.comm.entity.CommLog;
import org.pzy.archetypesystem.base.module.comm.mapstruct.CommLogMapStruct;
import org.pzy.archetypesystem.base.module.comm.service.CommLogService;
import org.pzy.archetypesystem.base.module.comm.vo.CommLogVO;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.mybatisplus.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * comm_log 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-06
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = CommLogServiceImpl.CACHE_NAME)
public class CommLogServiceImpl extends ServiceTemplate<CommLogDAO, CommLog> implements CommLogService {

    public static final String CACHE_NAME = "CommLogServiceImpl";

    @Autowired
    private CommLogMapStruct mapStruct;

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
    public PageT<CommLogVO> pageAndCache(CommLogSearchDTO dto) {
        if (null == dto) {
            return PageT.EMPTY();
        }
        // 查询关键词去空格
        CommLogSearchDTO condition = mapStruct.searchDtoToSearchDTO(dto);
        // 系统的分页条件转换为mybatis plus的分页条件
        IPage<CommLog> mybatisPlusPageCondition = toMybatisPlusPage(condition.getPg());
        // 构建mybatis plus查询条件
        QueryWrapper<CommLog> queryWrapper = buildQueryWrapper();
        if(null!=dto.getUseTime()){
            queryWrapper.ge(CommLog.USE_TIME, dto.getUseTime());
        }
        if(!StringUtils.isEmpty(dto.getKw())){
            String tmpKw = super.keywordEscape(dto.getKw());
            queryWrapper.like(CommLog.METHOD_FULL_NAME,tmpKw);
        }
        // mybatis plus分页查询
        IPage<CommLog> mybatisPlusPageResult = super.page(mybatisPlusPageCondition, queryWrapper);
        // mybatis plus分页结果, 转系统分页结果
        List<CommLogVO> voList = this.mapStruct.entityToDTO(mybatisPlusPageResult.getRecords());
        return PageUtil.mybatisPlusPage2PageT(mybatisPlusPageResult, voList);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    @Async
    public Long saveAndClearCache(@Valid @NotNull CommLogAddDTO dto) {
        // 对象转换
        CommLog entity = mapStruct.addSourceToEntity(dto);
        // 持久化
        boolean optSuc = super.save(entity);
        return entity.getId();
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public CommLogVO getByIdAndCache(Serializable id) {
        if (null == id) {
            return null;
        }
        CommLog entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

}
