package org.pzy.archetypesystem.base.support.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 发送密码修改验证码事件
 *
 * @author pan
 * @date 2020/4/4 09:09
 */
public class ChangePasswordSendValidateCodeEvent extends ApplicationEvent {

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 构造函数
     *
     * @param source 事件源
     * @param email  用户邮件
     */
    public ChangePasswordSendValidateCodeEvent(Object source, String email) {
        super(source);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
