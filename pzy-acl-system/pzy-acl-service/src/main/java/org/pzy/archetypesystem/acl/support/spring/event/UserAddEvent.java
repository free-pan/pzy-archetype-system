package org.pzy.archetypesystem.acl.support.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 新增用户事件
 *
 * @author pan
 * @date 2020/3/27
 */
public class UserAddEvent extends ApplicationEvent {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建一个事件对象
     *
     * @param source 触发事件的对象
     * @param userId 用户id
     */
    public UserAddEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
