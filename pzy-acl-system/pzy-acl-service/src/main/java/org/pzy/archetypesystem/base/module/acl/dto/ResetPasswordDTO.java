package org.pzy.archetypesystem.base.module.acl.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 用于忘记密码时进行密码重置
 *
 * @author pan
 * @date 2020/4/6 13:23
 */
@Data
public class ResetPasswordDTO extends AbstractModifyPasswordDTO {

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message = "请输入邮箱!")
    @Email(message = "邮箱格式错误!")
    private String email;

    @ApiModelProperty(value = "验证码", required = true)
    @NotBlank(message = "请输入验证码!")
    private String verifyCode;
}
