package org.pzy.archetypesystem.base.module.comm.enums;

import org.pzy.opensource.domain.entity.BaseEnum;

/**
 * 功能标识
 *
 * @author pan
 * @date 2020/4/6 17:42
 */
public enum FunCodeEnum implements BaseEnum<String> {
    LOGIN("登录"),
    LOGOUT("登出"),
    SEND_RESET_PWD_VERIFY_CODE("发送重置密码验证码"),
    RESET_PWD("重置密码"),
    /**
     * 公共日志搜索
     */
    CommLogSearch("公共日志搜索");

    /**
     * 功能名称
     */
    private String code;

    FunCodeEnum(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }
}
