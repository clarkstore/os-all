package com.onestop.common.core.annotation;

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
    String key() default "";
    /**
     * 单位时间限制通过请求数
     */
    long limitCount() default 10;

    /**
     * 单位时间，单位秒
     */
    long limitSec() default 5;
}
