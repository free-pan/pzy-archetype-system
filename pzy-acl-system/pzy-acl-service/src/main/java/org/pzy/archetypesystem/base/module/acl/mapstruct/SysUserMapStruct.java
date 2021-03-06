package org.pzy.archetypesystem.base.module.acl.mapstruct;

import org.mapstruct.Mapper;
import org.pzy.archetypesystem.base.module.acl.dto.*;
import org.pzy.archetypesystem.base.module.acl.entity.*;
import org.pzy.archetypesystem.base.module.acl.vo.*;
import org.pzy.opensource.comm.mapstruct.ComplexMapStruct;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;

/**
 * sys_user表相关领域对象转化类
 *
 * @author pan
 * @since 2020-04-05
 */
@Mapper(componentModel = "spring", uses = {StringDataMapper.class})
public interface SysUserMapStruct extends ComplexMapStruct<SysUserSearchDTO, SysUserAddDTO, SysUserEditDTO, SysUser, SysUserVO> {


}
