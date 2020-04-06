package org.pzy.archetypesystem.base.module.comm.mapstruct;

import org.mapstruct.Mapper;
import org.pzy.archetypesystem.base.module.comm.vo.*;
import org.pzy.archetypesystem.base.module.comm.entity.*;
import org.pzy.archetypesystem.base.module.comm.dto.*;
import org.pzy.opensource.comm.mapstruct.ComplexMapStruct;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;

/**
 * comm_online_user表相关领域对象转化类
 *
 * @author pan
 * @since 2020-04-06
 */
@Mapper(componentModel = "spring", uses = {StringDataMapper.class})
public interface CommOnlineUserMapStruct extends ComplexMapStruct<CommOnlineUserSearchDTO, CommOnlineUserAddDTO, CommOnlineUserEditDTO, CommOnlineUser, CommOnlineUserVO> {


}
