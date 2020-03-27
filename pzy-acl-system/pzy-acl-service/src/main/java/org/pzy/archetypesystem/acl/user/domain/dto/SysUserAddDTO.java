package org.pzy.archetypesystem.acl.user.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SysUserAddDTO: 用于新增操作, client --> server 数据传输类
 *
 * @author pan
 * @since 2020-03-24 16:44:51
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class SysUserAddDTO extends AbstractSysUserAddEditDTO {

}