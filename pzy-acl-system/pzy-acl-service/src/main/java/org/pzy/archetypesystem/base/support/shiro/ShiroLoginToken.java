package org.pzy.archetypesystem.base.support.shiro;

import lombok.Data;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * LoginToken
 *
 * @author pan
 * @date 2020/4/5 19:13
 */
@Data
public class ShiroLoginToken extends LoginParamsDTO implements RememberMeAuthenticationToken {

    public ShiroLoginToken() {
        this.setRememberMe(false);
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
