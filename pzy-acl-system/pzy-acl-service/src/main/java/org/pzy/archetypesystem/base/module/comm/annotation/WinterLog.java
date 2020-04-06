package org.pzy.archetypesystem.base.module.comm.annotation;

import org.pzy.archetypesystem.base.module.comm.enums.FunCodeEnum;
import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通用日志注解
 *
 * @author pan
 * @date 2020/4/6 17:39
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WinterLog {

    /**
     * 日志类型
     *
     * @return
     */
    WinterLogType type() default WinterLogType.Operate;

    /**
     * 功能标识
     *
     * @return
     */
    FunCodeEnum code();
}
