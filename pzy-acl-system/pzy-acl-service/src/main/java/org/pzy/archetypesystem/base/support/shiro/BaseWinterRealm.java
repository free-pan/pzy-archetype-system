/**
 * Copyright (C): 恒大集团版权所有 Evergrande Group
 */
package org.pzy.archetypesystem.base.support.shiro;

import lombok.extern.slf4j.Slf4j;
import org.pzy.opensource.security.service.ShiroWinterUserService;
import org.pzy.opensource.security.shiro.realm.WinterRealmTemplate;

/**
 * BaseWinterRealmTemplate
 *
 * @author pan
 * @date 2020/4/5 19:12
 */
@Slf4j
public class BaseWinterRealm extends WinterRealmTemplate {

    /**
     * 构造方法
     *
     * @param shiroWinterUserService 该实例用于实现根据登录信息查找用户信息,以及根据ShiroUserBO对象查找查找权限信息
     */
    public BaseWinterRealm(ShiroWinterUserService shiroWinterUserService) {
        super(shiroWinterUserService, ShiroLoginToken.class);
        log.debug("BaseWinterRealm初始化!");
    }
}
