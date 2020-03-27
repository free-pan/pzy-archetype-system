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
     * 用户姓名
     */
    private String name;

    /**
     * 创建一个事件对象
     *
     * @param source 触发事件的对象
     * @param userId 用户id
     * @param name   用户姓名
     */
    public UserAddEvent(Object source, Long userId, String name) {
        super(source);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
