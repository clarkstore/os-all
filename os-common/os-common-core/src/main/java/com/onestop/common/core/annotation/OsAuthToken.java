package com.onestop.common.core.annotation;

import java.lang.annotation.*;

/**
 * 验证Token
 * @author Clark
 * @version 2021/5/8
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OsAuthToken {
    boolean required() default true;
}
