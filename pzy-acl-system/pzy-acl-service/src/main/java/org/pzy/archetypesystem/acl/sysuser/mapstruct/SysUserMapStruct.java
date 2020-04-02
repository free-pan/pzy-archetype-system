package org.pzy.archetypesystem.acl.sysuser.mapstruct;

import org.mapstruct.Mapper;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserAddDTO;
import org.pzy.archetypesystem.acl.sysuser.dto.SysUserEditDTO;
import org.pzy.archetypesystem.acl.sysuser.entity.SysUser;
import org.pzy.archetypesystem.acl.sysuser.vo.SysUserVO;
import org.pzy.opensource.comm.mapstruct.BaseMapStruct;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;

/**
 * (sys_user)表相关领域对象转化类
 *
 * @author pan
 * @since 2020-03-24 16:49:38
 */
@Mapper(componentModel = "spring", uses = {StringDataMapper.class})
public interface SysUserMapStruct extends BaseMapStruct<SysUserAddDTO, SysUserEditDTO, SysUser, SysUserVO> {


}
