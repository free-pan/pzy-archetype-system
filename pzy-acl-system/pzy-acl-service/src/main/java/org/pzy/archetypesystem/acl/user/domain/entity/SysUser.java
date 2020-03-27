package org.pzy.archetypesystem.acl.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.pzy.opensource.mybatisplus.model.entity.BaseEntity;

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
public class SysUser extends BaseEntity {

    /**
    * 姓名
    * 对应列: name
    */
    @TableField(value="name")
    @ApiModelProperty(value="姓名")
    private String name;

    /**
    * 邮箱
    * 对应列: email
    */
    @TableField(value="email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
    * 密码
    * 对应列: password
    */
    @TableField(value="password")
    @ApiModelProperty(value="密码")
    private String password;

    /**
    * 备注信息
    * 对应列: remark
    */
    @TableField(value="remark")
    @ApiModelProperty(value="备注信息")
    private String remark;


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
    * 备注信息
    */
    public static final String REMARK = "remark";

}