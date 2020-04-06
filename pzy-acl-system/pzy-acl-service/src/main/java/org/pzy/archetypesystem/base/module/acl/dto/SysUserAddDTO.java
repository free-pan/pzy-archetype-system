package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * sys_user 表DTO类: 用于新增操作时接收客户端参数
 *
 * @author pan
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel
public class SysUserAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名. 长度:200", required = true)
    @NotBlank(message = "请输入姓名!")
    @Length(max = 200, message = "请输入{max}字符以内的姓名!")
    private String name;

    @ApiModelProperty(value = "邮箱. 长度:200", required = true)
    @NotBlank(message = "请输入邮箱!")
    @Email(message = "请输入正确的邮箱地址!")
    @Length(max = 200, message = "请输入{max}字符以内的邮箱!")
    private String email;

}
