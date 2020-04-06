package org.pzy.archetypesystem.base.support.shiro;

import org.mapstruct.Mapper;
import org.pzy.opensource.comm.mapstruct.StringDataMapper;

/**
 * ShiroMapStruct
 *
 * @author pan
 * @date 2020/4/6 15:40
 */
@Mapper(componentModel = "spring", uses = {StringDataMapper.class})
public interface ShiroMapStruct {
    ShiroLoginToken loginParamsDTO2ShiroLoginToken(LoginParamsDTO dto);
}
