package org.pzy.archetypesystem.acl.support.spring.publish;

import org.pzy.archetypesystem.acl.support.spring.event.UserAddEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件发布器
 *
 * @author pan
 * @date 2020/3/27
 */
@Component
public class WinterEventPublish {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 发布用户新增事件
     *
     * @param id   用户id
     * @param name 用户姓名
     */
    public void publishAddUserEvent(Long id, String name) {
        applicationContext.publishEvent(new UserAddEvent(this, id, name));
    }
}
