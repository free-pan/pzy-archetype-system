package org.pzy.archetypesystem.base.module.acl.dto;

import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.*;

/**
 * sys_role 表DTO类: 用于编辑操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class SysRoleEditDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "角色编码. 必须唯一")
    private String code;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "主键")
    private Long id;

}
