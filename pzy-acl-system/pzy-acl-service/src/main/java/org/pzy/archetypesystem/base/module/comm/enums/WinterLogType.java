package org.pzy.archetypesystem.base.module.comm.enums;

/**
 * 通用日志类型
 *
 * @author pan
 * @date 2020/4/6 17:39
 */
public enum WinterLogType {
    /**
     * 操作日志
     */
    Operate(1),
    /**
     * 登录日志
     */
    Login(2);

    private Integer type;

    WinterLogType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
