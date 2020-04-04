package org.pzy.archetypesystem.acl.support.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 发送密码修改验证码事件
 *
 * @author pan
 * @date 2020/4/4 09:09
 */
public class ChangePasswordSendValidateCodeEvent extends ApplicationEvent {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 构造函数
     *
     * @param source 事件源
     * @param userId 用户id
     * @param email  用户邮件
     */
    public ChangePasswordSendValidateCodeEvent(Object source, Long userId, String email) {
        super(source);
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
