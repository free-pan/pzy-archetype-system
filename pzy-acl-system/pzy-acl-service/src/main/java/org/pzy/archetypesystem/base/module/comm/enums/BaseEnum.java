package org.pzy.archetypesystem.base.module.comm.enums;

/**
 * 系统枚举最上层接口, 定义枚举的公共方法
 *
 * @author pan
 * @date 4/11/20
 */
public interface BaseEnum<T> {

    /**
     * 设置自定义编码或中文名称.
     *
     * @param code 编码或中文名称
     */
    void setCode(T code);

    /**
     * 获取自定义编码或中文名称
     *
     * @return 编码或中文名称
     */
    T getCode();

    /**
     * 获取枚举名称. 如: Color.red 枚举, 则这里会返回red
     *
     * @return 枚举名称
     */
    String name();
}
