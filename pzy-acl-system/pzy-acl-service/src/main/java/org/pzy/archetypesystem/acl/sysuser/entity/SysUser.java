package org.pzy.archetypesystem.acl.sysuser.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.pzy.opensource.mybatisplus.model.entity.LogicDelBaseEntity;

/**
 * (sys_user)表实体类
 *
 * @author pan
 * @since 2020-03-24 16:44:51
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUser extends LogicDelBaseEntity {

    /**
    * <p>姓名
    * <p>对应列: name
    */
    @TableField(value="name")
    @ApiModelProperty(value="姓名")
    private String name;

    /**
    * <p>邮箱
    * <p>对应列: email
    */
    @TableField(value="email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
    * <p>密码
    * <p>对应列: password
    */
    @TableField(value="password")
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * <p>是否激活. 0.未激活 1.已激活
     * <p>对应列: active
     */
    @TableField(value="active")
    @ApiModelProperty(value="是否激活")
    private Short active;

    /**
    * 姓名
    */
    public static final String NAME = "name";
    /**
    * 邮箱
    */
    public static final String EMAIL = "email";
    /**
    * 密码
    */
    public static final String PASSWORD = "password";
    /**
     * 是否激活. 0.未激活 1.已激活
     */
    public static final String ACTIVE = "active";

}