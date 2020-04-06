package org.pzy.archetypesystem.base.module.comm.enums;

/**
 * 功能标识
 *
 * @author pan
 * @date 2020/4/6 17:42
 */
public enum FunCodeEnum {
    ;

    /**
     * 功能编码
     */
    private String code;
    /**
     * 功能名
     */
    private String name;

    FunCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
