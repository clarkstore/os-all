package com.onestop.common.core.annotation;

import com.onestop.common.core.constant.OsLimitTypeEnum;

import java.lang.annotation.*;

/**
 * 限流
 *
 * @author Clark
 * @version 2021/5/8
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OsAccessLimit {
    /**
     * 自定义Key
     */
    String key() default "";
    /**
     * 单位时间限制通过请求数、默认200次
     */
    long limitCount() default 200;

    /**
     * 单位时间，单位秒、默认10秒
     */
    long limitSec() default 10;

    /**
     * 限制类型、默认方法名为Key
     */
    OsLimitTypeEnum limitType() default OsLimitTypeEnum.METHOD_NAME;
}
