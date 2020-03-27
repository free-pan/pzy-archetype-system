package org.pzy.archetypesystem.acl.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.pzy.archetypesystem.acl.user.domain.dto.EditPasswordDTO;
import org.pzy.archetypesystem.acl.user.domain.dto.ForgetPasswordDTO;
import org.pzy.archetypesystem.acl.user.domain.dto.SysUserAddDTO;
import org.pzy.archetypesystem.acl.user.domain.dto.SysUserEditDTO;
import org.pzy.archetypesystem.acl.user.domain.entity.SysUser;
import org.pzy.archetypesystem.acl.user.domain.vo.SimpleSysUserVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * (sys_user)表服务接口
 *
 * @author pan
 * @since 2020-03-24 16:49:38
 */
@Validated
public interface SysUserService extends IService<SysUser> {

    /**
     * 新增
     *
     * @param dto 待新增数据
     * @return 新增数据id
     * @author pan
     * @since 2020-03-24 16:49:38
     */
    Long add(@Valid @NotNull SysUserAddDTO dto);

    /**
     * 编辑
     *
     * @param dto 待更新数据
     * @return 是否编辑成功
     * @author pan
     * @since 2020-03-24 16:49:38
     */
    boolean edit(@Valid @NotNull SysUserEditDTO dto);

    /**
     * 根据id查找简单的用户信息
     *
     * @param id 主键
     * @return 简单用户信息
     */
    SimpleSysUserVO searchSimpleUserById(@Valid @NotNull Long id);

    /**
     * 根据id集合查找简单的用户信息
     *
     * @param idColl 主键集合
     * @return 简单用户信息
     */
    List<SimpleSysUserVO> searchSimpleUserByIds(@Valid @NotEmpty Collection<Long> idColl);

    /**
     * 根据id集合查找简单的用户信息
     *
     * @param idColl 主键集合
     * @return key为用户id
     */
    Map<Long, SimpleSysUserVO> searchSimpleUserByIdsMap(@Valid @NotEmpty Collection<Long> idColl);

    /**
     * 根据邮箱查找简单的用户信息
     *
     * @param email 邮箱
     * @return 简单用户信息
     */
    SimpleSysUserVO searchSimpleUserByEmail(@Valid @NotBlank String email);

    /**
     * 根据id集合查找简单的用户信息
     *
     * @param emailColl 邮箱集合
     * @return 简单用户信息
     */
    List<SimpleSysUserVO> searchSimpleUserByEmails(@Valid @NotEmpty Collection<String> emailColl);

    /**
     * 通过id修改密码
     *
     * @param editPasswordDTO 密码相关信息
     */
    void editPasswordById(@Valid @NotNull EditPasswordDTO editPasswordDTO);

    /**
     * 通过邮件修改密码
     *
     * @param forgetPasswordDTO 密码相关信息
     */
    void editPasswordByEmail(@Valid @NotNull ForgetPasswordDTO forgetPasswordDTO);
}
