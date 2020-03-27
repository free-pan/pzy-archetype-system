package org.pzy.archetypesystem.acl.user.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * SysUserEditDTO: 用于编辑操作, client --> server 数据传输类
 *
 * @author pan
 * @since 2020-03-24 16:44:51
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class SysUserEditDTO extends AbstractSysUserAddEditDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

}