package org.pzy.archetypesystem.acl.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.pzy.archetypesystem.acl.user.dao.SysUserDAO;
import org.pzy.archetypesystem.acl.user.domain.dto.*;
import org.pzy.archetypesystem.acl.user.domain.entity.SysUser;
import org.pzy.archetypesystem.acl.user.domain.vo.SimpleSysUserVO;
import org.pzy.archetypesystem.acl.user.mapstruct.SimpleSysUserMapStruct;
import org.pzy.archetypesystem.acl.user.mapstruct.SysUserMapStruct;
import org.pzy.archetypesystem.acl.user.service.SysUserService;
import org.pzy.opensource.comm.exception.ValidateException;
import org.pzy.opensource.comm.util.RandomPasswordUtil;
import org.pzy.opensource.security.shiro.matcher.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserDAO, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapStruct mapStruct;
    @Autowired
    private SimpleSysUserMapStruct simpleSysUserMapStruct;

    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Override
    public Long add(@Valid @NotNull SysUserAddDTO dto) {
        // TODO 邮箱是否唯一检测
        // 对象转换
        SysUser entity = mapStruct.addSourceToEntity(dto);
        // 生成6位随机密码
        String pwd = RandomPasswordUtil.generateSixRandomPassword();
        entity.setPassword(PASSWORD_ENCODER.encode(pwd));
        // 持久化
        super.save(entity);
        if (log.isDebugEnabled()) {
            log.debug("用户 id:[%s], email:[%s], pwd:[%s]", entity.getId(), entity.getEmail(), entity.getPassword());
        }
        // TODO 发送账号激活邮件
        return entity.getId();
    }

    @Override
    public boolean edit(@Valid @NotNull SysUserEditDTO dto) {
        // 验证用户信息是否存在
        this.validateUserExists(dto.getId());
        // 对象转换
        SysUser entity = mapStruct.editSourceToEntity(dto);
        // 持久化
        return super.updateById(entity);
    }

    @Override
    public SimpleSysUserVO searchSimpleUserById(@Valid @NotNull Long id) {
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

    @Override
    public List<SimpleSysUserVO> searchSimpleUserByIds(@Valid @NotEmpty Collection<Long> idColl) {
        QueryWrapper<SysUser> queryWrapper = buildSimpleSysUserVOSelectQueryWrapper();
        queryWrapper.in(SysUser.ID, idColl);
        return queryListEntityToSimpleSysUserVO(queryWrapper);
    }

    @Override
    public Map<Long, SimpleSysUserVO> searchSimpleUserByIdsMap(@Valid @NotEmpty Collection<Long> idColl) {
        List<SimpleSysUserVO> simpleSysUserList = this.searchSimpleUserByIds(idColl);
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

    @Override
    public SimpleSysUserVO searchSimpleUserByEmail(@Valid @NotBlank String email) {
        QueryWrapper<SysUser> queryWrapper = buildSimpleSysUserVOSelectQueryWrapper();
        queryWrapper.eq(SysUser.EMAIL, email);
        return this.queryOneEntityToSimpleSysUserVO(queryWrapper);
    }

    @Override
    public List<SimpleSysUserVO> searchSimpleUserByEmails(@Valid @NotEmpty Collection<String> emailColl) {
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

    @Override
    public void editPasswordById(@Valid @NotNull EditPasswordDTO editPasswordDTO) {
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
    public void editPasswordByEmail(@Valid @NotNull ForgetPasswordDTO forgetPasswordDTO) {

    }
}
