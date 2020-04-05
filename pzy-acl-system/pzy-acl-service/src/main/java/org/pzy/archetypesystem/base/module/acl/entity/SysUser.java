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
 * sys_user 表实体类:用户
 *
 * @author pan
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel
public class SysUser extends LogicDelBaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "是否激活. 0.未激活  1.已激活")
    @TableField("active")
    private Integer active;

    public static final String NAME = "name";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String ACTIVE = "active";

}
