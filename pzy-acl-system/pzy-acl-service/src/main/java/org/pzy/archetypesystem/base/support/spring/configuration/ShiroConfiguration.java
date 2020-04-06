package org.pzy.archetypesystem.base.support.spring.configuration;

import org.pzy.archetypesystem.base.support.shiro.BaseWinterRealm;
import org.pzy.opensource.security.service.ShiroWinterUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * BeanConfiguration
 *
 * @author pan
 * @date 2020/4/5 19:26
 */
@Component
public class ShiroConfiguration {

    @Bean
    BaseWinterRealm baseWinterRealm(ShiroWinterUserService shiroWinterUserService) {
        return new BaseWinterRealm(shiroWinterUserService);
    }
}
