package org.pzy.archetypesystem.acl.sysuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.acl.support.spring.event.*;
import org.pzy.archetypesystem.acl.support.spring.listener.CustomEventListener;
import org.pzy.archetypesystem.acl.sysuser.dao.*;
import org.pzy.archetypesystem.acl.sysuser.dto.*;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.pzy.archetypesystem.acl.sysuser.mapstruct.*;
import org.pzy.archetypesystem.acl.sysuser.service.SysUserService;
import org.pzy.archetypesystem.acl.sysuser.vo.SimpleSysUserVO;
import org.pzy.archetypesystem.acl.sysuser.vo.SysUserVO;
import org.pzy.opensource.comm.exception.ValidateException;
import org.pzy.opensource.comm.util.RandomPasswordUtil;
import org.pzy.opensource.domain.GlobalConstant;
import org.pzy.opensource.domain.PageT;
import org.pzy.opensource.mybatisplus.service.ServiceTemplateImpl;
import org.pzy.opensource.mybatisplus.util.SpringUtil;
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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * (sys_user)表服务实现
 *
 * @author pan
 * @since 2020-03-24 16:49:38
 */
@Slf4j
@Service
@Validated
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
@CacheConfig(cacheNames = "SysUserServiceImpl")
public class SysUserServiceImpl extends ServiceTemplateImpl<SysUserDAO, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapStruct mapStruct;
    @Autowired
    private SimpleSysUserMapStruct simpleSysUserMapStruct;

    public SysUserServiceImpl() {
        System.out.println("无参构造方法");
    }

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @WinterLock(lockBuilder = @LockBuilder(condition = "[0].email!=null"))
    @Override
    public Long addClearCache(@Valid @NotNull SysUserAddDTO dto) {
        // 检测邮箱是否唯一
        int emailCount = super.getBaseMapper().selectEmailCount(dto.getEmail());
        if (emailCount > 0) {
            throw new ValidateException("该邮箱已使用!");
        }
        // 对象转换
        SysUser entity = mapStruct.addSourceToEntity(dto);
        // 生成6位随机密码
        String pwd = RandomPasswordUtil.generateSixRandomPassword();
        entity.setPassword(PASSWORD_ENCODER.encode(pwd));
        // 持久化
        boolean optSuc = super.save(entity);
        if (log.isDebugEnabled()) {
            log.debug("用户 id:[{}], email:[{}], pwd:[{}]", entity.getId(), entity.getEmail(), entity.getPassword());
        }
        // 发送账号激活邮件
        if (optSuc) {
            SpringUtil.publishEventOnAfterCommitIfNecessary(new UserAddEvent(this, entity.getId()));
        }
        return entity.getId();
    }

    @Override
    public void activeAccount(String activeCode) {
        Long id = (Long) RedisUtil.get(activeCode);
        if (null == id) {
            throw new ValidateException("无效的激活码!");
        }
        RedisUtil.remove(activeCode);
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setActive(GlobalConstant.ACTIVE);
        SysUserService serviceProxy = (SysUserService) super.getCurrentBeanProxy();
        serviceProxy.editAndClearCache(sysUser);
    }

    @Override
    public void sendActiveEmailRetry(@Valid @Email(message = "请输入有效的邮箱地址!") @NotBlank(message = "请输入有效的邮箱地址!") String toEmail) {
        SysUser entity = this.getSysUserByEmail(toEmail);
        if (null != entity) {
            if (entity.getActive() == GlobalConstant.ACTIVE) {
                throw new ValidateException("账号已激活,无需再次激活!");
            } else {
                SpringUtil.publishEventOnAfterCommitIfNecessary(new UserAddEvent(this, entity.getId()));
            }
        } else {
            log.warn("邮箱[{}]尚未注册或使用该邮箱注册的账号已不可用!", toEmail);
            throw new ValidateException("无效的邮件接收地址, 无法发送激活邮件!");
        }
    }

    /**
     * 通过邮箱查找用户信息
     *
     * @param toEmail
     * @return
     */
    private SysUser getSysUserByEmail(String toEmail) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SysUser.EMAIL, toEmail);
        return super.getOne(queryWrapper);
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public boolean editClearCache(@Valid @NotNull SysUserEditDTO dto) {
        // 验证用户信息是否存在
        this.validateUserExists(dto.getId());
        // 对象转换
        SysUser entity = mapStruct.editSourceToEntity(dto);
        // 持久化
        return super.updateById(entity);
    }

    @Cacheable(sync = true)
    @Override
    public SimpleSysUserVO searchSimpleUserByIdAndCache(@Valid @NotNull Long id) {
        SysUserService proxy = (SysUserService) this.getCurrentBeanProxy();
        System.out.println("代理对象:" + proxy);
        QueryWrapper<SysUser> queryWrapper = buildSimpleSysUserVOSelectQueryWrapper();
        queryWrapper.eq(SysUser.ID, id);
        return queryOneEntityToSimpleSysUserVO(queryWrapper);
    }

    /**
     * 按条件单个查询
     *
     * @param queryWrapper 查询条件
     * @return 单个查询结果
     */
    private SimpleSysUserVO queryOneEntityToSimpleSysUserVO(QueryWrapper<SysUser> queryWrapper) {
        SysUser sysUser = super.getOne(queryWrapper);
        return simpleSysUserMapStruct.sourceToTarget(sysUser);
    }

    /**
     * 构建SimpleSysUserVO查询字段
     *
     * @return 查询列
     */
    private QueryWrapper<SysUser> buildSimpleSysUserVOSelectQueryWrapper() {
        return new QueryWrapper<SysUser>().select(SysUser.ID, SysUser.EMAIL, SysUser.NAME);
    }

    @Cacheable(sync = true)
    @Override
    public List<SimpleSysUserVO> searchSimpleUserByIdsAndCache(@Valid @NotEmpty Collection<Long> idColl) {
        QueryWrapper<SysUser> queryWrapper = buildSimpleSysUserVOSelectQueryWrapper();
        queryWrapper.in(SysUser.ID, idColl);
        return queryListEntityToSimpleSysUserVO(queryWrapper);
    }

    @Cacheable(sync = true)
    @Override
    public Map<Long, SimpleSysUserVO> searchSimpleUserByIdsMapAndCache(@Valid @NotEmpty Collection<Long> idColl) {
        List<SimpleSysUserVO> simpleSysUserList = this.searchSimpleUserByIdsAndCache(idColl);
        // list转map
        return simpleSysUserList.parallelStream().collect(Collectors.toMap(SimpleSysUserVO::getId, item -> item, (k1, k2) -> k1));
    }

    /**
     * 按条件集合查询
     *
     * @param queryWrapper 查询条件
     * @return 查询结果集合
     */
    private List<SimpleSysUserVO> queryListEntityToSimpleSysUserVO(QueryWrapper<SysUser> queryWrapper) {
        List<SysUser> sysUserList = super.list(queryWrapper);
        return simpleSysUserMapStruct.sourceToTarget(sysUserList);
    }

    @Cacheable(sync = true)
    @Override
    public SimpleSysUserVO searchSimpleUserByEmailAndCache(@Valid @NotBlank String email) {
        QueryWrapper<SysUser> queryWrapper = buildSimpleSysUserVOSelectQueryWrapper();
        queryWrapper.eq(SysUser.EMAIL, email);
        return this.queryOneEntityToSimpleSysUserVO(queryWrapper);
    }

    @Cacheable(sync = true)
    @Override
    public List<SimpleSysUserVO> searchSimpleUserByEmailsAndCache(@Valid @NotEmpty Collection<String> emailColl) {
        QueryWrapper<SysUser> queryWrapper = buildSimpleSysUserVOSelectQueryWrapper();
        queryWrapper.in(SysUser.EMAIL, emailColl);
        return this.queryListEntityToSimpleSysUserVO(queryWrapper);
    }

    /**
     * 新密码验证
     *
     * @param abstractChangePwdDTO
     */
    private void validateNewPwd(AbstractChangePwdDTO abstractChangePwdDTO) {
        if (!abstractChangePwdDTO.getNewPwd().equals(abstractChangePwdDTO.getNewPwdConfirm())) {
            throw new ValidateException("两次输入的密码不一致!");
        }
    }

    /**
     * 验证用户是否存在
     *
     * @param id 用户id
     * @return 用户信息
     */
    private SysUser validateUserExists(Long id) {
        SysUser sysUser = this.baseMapper.selectById(id);
        if (null == sysUser) {
            throw new ValidateException("未找到所需的用户信息!");
        }
        return sysUser;
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void editPasswordByIdAndClearCache(@Valid @NotNull EditPasswordDTO editPasswordDTO) {
        SysUser sysUser = this.validateUserExists(editPasswordDTO.getId());
        if (!PASSWORD_ENCODER.matches(editPasswordDTO.getOldPwd(), sysUser.getPassword())) {
            throw new ValidateException("原始密码错误!");
        }
        this.validateNewPwd(editPasswordDTO);
        // 更新密码
        SysUser entity = new SysUser();
        entity.setId(editPasswordDTO.getId());
        entity.setPassword(PASSWORD_ENCODER.encode(editPasswordDTO.getNewPwd()));
        this.updateById(entity);
    }

    @Override
    public void sendChangePasswordValidateCodeEmail(@Valid @Email(message = "请输入有效的邮箱地址!") @NotBlank(message = "请输入有效的邮箱地址!") String toEmail) {
        SysUser entity = this.getSysUserByEmail(toEmail);
        if (null != entity) {
            SpringUtil.publishEventOnAfterCommitIfNecessary(new UserAddEvent(this, entity.getId()));
        } else {
            log.warn("邮箱[{}]尚未注册或使用该邮箱注册的账号已不可用!", toEmail);
            throw new ValidateException("邮箱对应的账号不存在或不可用!");
        }
    }

    @CacheEvict(allEntries = true, beforeInvocation = true)
    @Override
    public void editPasswordByEmailAndClearCache(@Valid @NotNull ForgetPasswordDTO forgetPasswordDTO) {
        this.validateNewPwd(forgetPasswordDTO);
        String key = CustomEventListener.buildPasswordModifyVerifyCodeRedisKey(forgetPasswordDTO.getEmail());
        String verifyCode = (String) RedisUtil.get(key);
        if (StringUtils.isEmpty(verifyCode)) {
            throw new ValidateException("无效的邮箱地址!");
        } else if (!forgetPasswordDTO.getCode().equalsIgnoreCase(verifyCode)) {
            throw new ValidateException("无效的验证码!");
        }
        // 根据邮箱修改密码
        SysUser entity = new SysUser();
        entity.setPassword(PASSWORD_ENCODER.encode(forgetPasswordDTO.getNewPwd()));
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SysUser.EMAIL, forgetPasswordDTO.getEmail());
        super.update(entity, queryWrapper);
    }

    @Override
    public PageT<SysUserVO> searchPageAndCache(SysUserSearchDTO dto) {
        return null;
    }
}
