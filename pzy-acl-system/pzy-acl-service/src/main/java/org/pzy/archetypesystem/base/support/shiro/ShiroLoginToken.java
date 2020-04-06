package org.pzy.archetypesystem.base.support.shiro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * LoginToken
 *
 * @author pan
 * @date 2020/4/5 19:13
 */
@Data
public class ShiroLoginToken implements RememberMeAuthenticationToken {

    @ApiModelProperty(value = "邮箱", required = true)
    @NotBlank(message = "请输入邮箱!")
    @Email(message = "邮箱格式错误!")
    private String email;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "请输入密码!")
    private String password;

    @ApiModelProperty(value = "是否记住我. 默认:false", required = true)
    private boolean rememberMe;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码. 密码错误次数大于3次,则需要输入验证码.")
    private String verifyCode;

    public ShiroLoginToken() {
        this.rememberMe = false;
    }

    @Override
    public boolean isRememberMe() {
        return this.rememberMe;
    }

    @Override
    public Object getPrincipal() {
        return this.getEmail();
    }

    @Override
    public Object getCredentials() {
        return this.getPassword().toCharArray();
    }
}
