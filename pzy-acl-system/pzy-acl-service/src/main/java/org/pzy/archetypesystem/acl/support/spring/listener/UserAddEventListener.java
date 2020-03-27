package org.pzy.archetypesystem.acl.support.spring.listener;

import org.pzy.archetypesystem.acl.support.spring.event.UserAddEvent;
import org.pzy.opensource.email.domain.bo.EmailMessageBO;
import org.pzy.opensource.email.domain.bo.EmailServerPropertiesBO;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 用户新增事件监听
 *
 * @author pan
 * @date 2020/3/27
 */
@Component
public class UserAddEventListener implements ApplicationListener<UserAddEvent> {

    @Override
    public void onApplicationEvent(UserAddEvent event) {
        EmailServerPropertiesBO emailServerPropertiesBO = new EmailServerPropertiesBO();
        EmailMessageBO emailMessageBO = new EmailMessageBO();

    }
}
