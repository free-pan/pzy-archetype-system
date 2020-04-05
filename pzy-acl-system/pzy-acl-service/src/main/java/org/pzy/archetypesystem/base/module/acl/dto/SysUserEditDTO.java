package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * sys_user 表DTO类: 用于编辑操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class SysUserEditDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "主键")
    private Long id;

}
