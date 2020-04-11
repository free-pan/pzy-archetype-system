package org.pzy.archetypesystem.base.module.comm.enums;

/**
 * 功能标识
 *
 * @author pan
 * @date 2020/4/6 17:42
 */
public enum FunCodeEnum {
    LOGIN("login", "登录"),
    LOGOUT("logout", "登出"),
    SEND_RESET_PWD_VERIFY_CODE("send_reset_pwd_verify_code","发送重置密码验证码"),
    RESET_PWD("reset_pwd","重置密码");
    /**
     * 功能编码
     */
    private String code;
    /**
     * 功能名
     */
    private String funName;

    FunCodeEnum(String code, String funName) {
        this.code = code;
        this.funName = funName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }
}
