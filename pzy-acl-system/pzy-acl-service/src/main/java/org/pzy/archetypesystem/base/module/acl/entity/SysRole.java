package org.pzy.archetypesystem.base.module.acl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

/**
 * sys_role 表实体类:角色
 *
 * @author pan
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel
public class SysRole extends LogicDelBaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "角色编码. 必须唯一")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "备注信息")
    @TableField("remark")
    private String remark;

    public static final String NAME = "name";

    public static final String CODE = "code";

    public static final String REMARK = "remark";

}
