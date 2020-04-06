package org.pzy.archetypesystem.base.support.shiro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * LoginParamsDTO
 *
 * @author pan
 * @date 2020/4/6 15:41
 */
@Data
@Accessors(chain = true)
public class LoginParamsDTO implements Serializable {

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message = "请输入邮箱!")
    @Email(message = "邮箱格式错误!")
    private String email;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "请输入密码!")
    private String password;

    @ApiModelProperty(value = "是否记住我. 默认:false", required = true)
    private boolean rememberMe;

    @ApiModelProperty(value = "验证码. 密码错误次数大于3次,则需要输入验证码.")
    private String verifyCode;
}
