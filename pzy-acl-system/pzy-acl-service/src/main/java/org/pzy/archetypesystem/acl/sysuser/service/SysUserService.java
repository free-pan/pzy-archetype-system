package org.pzy.archetypesystem.acl.sysuser.service;

import org.pzy.archetypesystem.acl.sysuser.dto.EditPasswordDTO;
import org.pzy.archetypesystem.acl.sysuser.dto.ForgetPasswordDTO;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserAddDTO;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserEditDTO;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.pzy.archetypesystem.acl.sysuser.vo.SimpleSysUserVO;
import org.pzy.opensource.mybatisplus.service.ServiceTemplate;
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
public interface SysUserService extends ServiceTemplate<SysUser> {

    /**
     * 新增, 并清除缓存
     *
     * @param dto 待新增数据
     * @return 新增数据id
     * @author pan
     * @since 2020-03-24 16:49:38
     */
    Long add(@Valid @NotNull SysUserAddDTO dto);

    /**
     * 编辑, 并清除缓存
     *
     * @param dto 待更新数据
     * @return 是否编辑成功
     * @author pan
     * @since 2020-03-24 16:49:38
     */
    boolean edit(@Valid @NotNull SysUserEditDTO dto);

    /**
     * 根据id查找简单的用户信息, 并放入缓存
     *
     * @param id 主键
     * @return 简单用户信息
     */
    SimpleSysUserVO searchSimpleUserById(@Valid @NotNull Long id);

    /**
     * 根据id集合查找简单的用户信息, 并放入缓存
     *
     * @param idColl 主键集合
     * @return 简单用户信息
     */
    List<SimpleSysUserVO> searchSimpleUserByIds(@Valid @NotEmpty Collection<Long> idColl);

    /**
     * 根据id集合查找简单的用户信息, 并放入缓存
     *
     * @param idColl 主键集合
     * @return key为用户id
     */
    Map<Long, SimpleSysUserVO> searchSimpleUserByIdsMap(@Valid @NotEmpty Collection<Long> idColl);

    /**
     * 根据邮箱查找简单的用户信息, 并放入缓存
     *
     * @param email 邮箱
     * @return 简单用户信息
     */
    SimpleSysUserVO searchSimpleUserByEmail(@Valid @NotBlank String email);

    /**
     * 根据id集合查找简单的用户信息, 并放入缓存
     *
     * @param emailColl 邮箱集合
     * @return 简单用户信息
     */
    List<SimpleSysUserVO> searchSimpleUserByEmails(@Valid @NotEmpty Collection<String> emailColl);

    /**
     * 通过id修改密码, 并清除缓存
     *
     * @param editPasswordDTO 密码相关信息
     */
    void editPasswordById(@Valid @NotNull EditPasswordDTO editPasswordDTO);

    /**
     * 通过邮件修改密码, 并清除缓存
     *
     * @param forgetPasswordDTO 密码相关信息
     */
    void editPasswordByEmail(@Valid @NotNull ForgetPasswordDTO forgetPasswordDTO);

}
