package org.pzy.archetypesystem.base.module.comm.enums;

/**
 * 操作结果枚举. 用于日志记录
 *
 * @author pan
 * @date 4/11/20
 */
public enum OptResultEnum {
    /**
     * 操作执行成功
     */
    SUCCESS(1),
    /**
     * 操作执行异常
     */
    EXP(2);
    private Integer code;

    OptResultEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
