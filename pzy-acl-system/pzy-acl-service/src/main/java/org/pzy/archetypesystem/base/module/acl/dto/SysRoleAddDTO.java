package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * sys_role 表DTO类: 用于新增操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class SysRoleAddDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色编码. 必须唯一")
    private String code;

    @ApiModelProperty(value = "备注信息")
    private String remark;

}
