package org.pzy.archetypesystem.base.module.comm.enums;

import org.pzy.opensource.domain.entity.BaseEnum;

/**
 * 通用日志类型
 *
 * @author pan
 * @date 2020/4/6 17:39
 */
public enum WinterLogType implements BaseEnum<Integer> {
    /**
     * 操作日志
     */
    Operate(1),
    /**
     * 登录日志
     */
    Login(2);

    private Integer code;

    WinterLogType(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

}
