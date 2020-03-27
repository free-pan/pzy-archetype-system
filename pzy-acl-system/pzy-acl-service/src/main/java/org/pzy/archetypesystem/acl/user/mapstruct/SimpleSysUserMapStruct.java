package org.pzy.archetypesystem.acl.user.mapstruct;

import org.mapstruct.Mapper;
import org.pzy.archetypesystem.acl.user.domain.entity.SysUser;
import org.pzy.archetypesystem.acl.user.domain.vo.SimpleSysUserVO;
import org.pzy.opensource.comm.mapstruct.SimpleBaseMapStruct;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;

/**
 * @author pan
 * @date 2020/3/26
 */
@Mapper(componentModel = "spring", uses = {StringDataMapper.class})
public interface SimpleSysUserMapStruct extends SimpleBaseMapStruct<SysUser, SimpleSysUserVO> {
}
