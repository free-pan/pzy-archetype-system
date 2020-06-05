package org.pzy.archetypesystem.base.module.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.base.module.acl.dao.SysUserDAO;
import org.pzy.archetypesystem.base.module.acl.dto.*;
import org.pzy.archetypesystem.base.module.acl.entity.SysUser;
import org.pzy.archetypesystem.base.module.acl.mapstruct.SysUserMapStruct;
import org.pzy.archetypesystem.base.module.acl.service.SysUserService;
import org.pzy.archetypesystem.base.module.acl.vo.SysUserVO;
import org.pzy.archetypesystem.base.module.acl.vo.UserEmailAndPwdVO;
import org.pzy.archetypesystem.base.support.spring.event.ChangePasswordSendValidateCodeEvent;
import org.pzy.archetypesystem.base.support.spring.event.UserAddEvent;
import org.pzy.archetypesystem.base.support.spring.listener.CustomEventListener;
import org.pzy.opensource.comm.exception.ValidateException;
import org.pzy.opensource.comm.util.RandomPasswordUtil;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
import org.pzy.opensource.mybatisplus.util.PageUtil;
import org.pzy.opensource.redis.support.springboot.annotation.LockBuilder;
import org.pzy.opensource.redis.support.springboot.annotation.WinterLock;
import org.pzy.opensource.redis.support.util.RedisUtil;
import org.pzy.opensource.security.shiro.matcher.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * sys_user 表相关服务实现类
 *
 * @author pan
 * @since 2020-04-05
 */
@Slf4j
@Service
@Validated
@CacheConfig(cacheNames = SysUserServiceImpl.CACHE_NAME)
public class SysUserServiceImpl extends ServiceTemplate<SysUserDAO, SysUser> implements SysUserService {

    public static final String CACHE_NAME = "SysUserServiceImpl";

    @Autowired
    private SysUserMapStruct mapStruct;

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

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
    public PageT<SysUserVO> pageAndCache(SysUserSearchDTO dto) {
        if (null == dto) {
            return PageT.EMPTY();
        }
        SysUserSearchDTO condition = mapStruct.searchDtoToSearchDTO(dto);
        // 系统的分页条件转换为mybatis plus的分页条件
        IPage<SysUser> mybatisPlusPageCondition = toMybatisPlusPage(condition.getPg());
        // 构建mybatis plus查询条件
        QueryWrapper<SysUser> queryWrapper = buildQueryWrapper();
        between(queryWrapper, SysUser.CREATE_TIME, condition);
        String kw = super.keywordEscape(condition.getKw());
        queryWrapper.like(!StringUtils.isEmpty(kw), SysUser.EMAIL, kw).or().like(!StringUtils.isEmpty(kw), SysUser.NAME, kw);
        // mybatis plus分页查询
        IPage<SysUser> mybatisPlusPageResult = super.page(mybatisPlusPageCondition, queryWrapper);
        // mybatis plus分页结果, 转系统分页结果
        List<SysUserVO> voList = this.mapStruct.entityToDTO(mybatisPlusPageResult.getRecords());
        return PageUtil.mybatisPlusPage2PageT(mybatisPlusPageResult, voList);
    }

    @CacheEvict(allEntries = true)
    @WinterLock(lockBuilder = @LockBuilder(condition = "[0].email!=null"))
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public Long saveAndClearCache(@Valid @NotNull SysUserAddDTO dto) {
        int emailCount = super.baseMapper.getEmailCount(dto.getEmail(), null);
        if (emailCount > 0) {
            throw new ValidateException(String.format("邮箱[%s]已使用!", dto.getEmail()));
        }
        // 对象转换
        SysUser entity = mapStruct.addSourceToEntity(dto);
        // 生成6位随机密码
        String pwd = RandomPasswordUtil.generateSixRandomPassword();
        entity.setPassword(PASSWORD_ENCODER.encode(pwd));
        entity.setActive(GlobalConstant.NOT_ACTIVE);
        // 持久化
        boolean optSuc = super.save(entity);
        if (optSuc) {
            super.publishEventOnAfterCommitIfNecessary(new UserAddEvent(this, entity.getId()));
        }
        return entity.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    public void sendActiveEmailAgain(@Valid @NotNull @Email String email) {
        SysUser entity = super.getOne(super.buildQueryWrapper().eq(SysUser.EMAIL, email));
        if (null == entity) {
            if (log.isWarnEnabled()) {
                log.warn("邮箱[{}]对应的账号不存在或已被删除!", email);
            }
            throw new ValidateException("无效的邮箱地址!");
        }
        if (GlobalConstant.ACTIVE.equals(entity.getActive())) {
            throw new ValidateException("该账号已激活!");
        }
        super.publishEventOnAfterCommitIfNecessary(new UserAddEvent(this, entity.getId()));
    }

    @Override
    @CacheEvict(allEntries = true)
    public UserEmailAndPwdVO activeAccountAndClearCache(@Valid @NotBlank String validateCode) {
        Long id = (Long) RedisUtil.get(validateCode);
        if (null == id) {
            throw new ValidateException("无效的激活码或激活码已过期!");
        }
        RedisUtil.remove(validateCode);
        SysUser sysUser = super.getById(id);
        if (GlobalConstant.ACTIVE.equals(sysUser.getActive())) {
            throw new ValidateException("账号已激活无需重复激活!");
        }
        String pwd = RandomPasswordUtil.generateSixRandomPassword();
        SysUser entity = new SysUser();
        entity.setActive(GlobalConstant.ACTIVE);
        // 生成随机密码
        entity.setPassword(PASSWORD_ENCODER.encode(pwd));
        entity.setId(id);
        super.updateById(entity);
        return new UserEmailAndPwdVO().setEmail(sysUser.getEmail()).setPwd(pwd);
    }

    @CacheEvict(allEntries = true)
    @Override
    public void modifyPasswordAndClearCache(@Valid @NotNull ModifyPasswordDTO dto) {
        String oldPwd = dto.getOldPwd().trim();
        String newPwd = dto.getNewPwd().trim();
        String confirmPwd = dto.getConfirmPwd().trim();
        if (!newPwd.equals(confirmPwd)) {
            throw new ValidateException("确认密码与新密码不一致!");
        }
        String encodedPassword = PASSWORD_ENCODER.encode(oldPwd);
        SysUser sysUser = super.getById(dto.getId());
        if (!PASSWORD_ENCODER.matches(sysUser.getPassword(), encodedPassword)) {
            throw new ValidateException("原始密码错误!");
        }
        // 设置为新密码
        sysUser = new SysUser();
        sysUser.setId(dto.getId());
        sysUser.setPassword(PASSWORD_ENCODER.encode(newPwd));
        super.updateById(sysUser);
    }

    @Override
    public void sendResetPasswordValidCode(@Valid @NotBlank @Email String email) {
        String tmpEmail = email.trim();
        int count = super.count(super.buildQueryWrapper().eq(SysUser.EMAIL, tmpEmail));
        if (count == 0) {
            throw new ValidateException("邮箱未注册或对应账号已被删除!");
        }
        super.publishEventOnAfterCommitIfNecessary(new ChangePasswordSendValidateCodeEvent(this, tmpEmail));
    }

    @Override
    @CacheEvict(allEntries = true)
    public void resetPasswordAndClearCache(@Valid @NotNull ResetPasswordDTO dto) {
        String newPwd = dto.getNewPwd().trim();
        String confirmPwd = dto.getConfirmPwd().trim();
        if (!newPwd.equals(confirmPwd)) {
            throw new ValidateException("确认密码与新密码不一致!");
        }
        String redisKey = CustomEventListener.buildPasswordModifyVerifyCodeRedisKey(dto.getEmail());
        String verifyCode = (String) RedisUtil.get(redisKey);
        if (null == verifyCode) {
            throw new ValidateException("验证码已失效!");
        }
        if (!dto.getVerifyCode().trim().equalsIgnoreCase(verifyCode)) {
            throw new ValidateException("验证码错误!");
        }
        // 设置为新密码
        String encodedPassword = PASSWORD_ENCODER.encode(newPwd);
        SysUser sysUser = new SysUser();
        sysUser.setPassword(encodedPassword);
        super.update(sysUser, buildQueryWrapper().eq(SysUser.EMAIL, dto.getEmail()));
    }

    @Cacheable(sync = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public SysUserVO getByIdAndCache(Serializable id) {
        if (null == id) {
            return null;
        }
        SysUser entity = super.getById(id);
        return this.mapStruct.entityToDTO(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateByIdAndClearCache(@Valid @NotNull SysUserEditDTO dto) {
        // 对象转换
        SysUser entity = this.mapStruct.editSourceToEntity(dto);
        return super.updateById(entity);
    }

    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean removeByIdAndClearCache(Serializable id) {
        if (null == id) {
            return false;
        }
        return super.baseMapper.winterLogicDeleteById(id) > 0;
    }
}
