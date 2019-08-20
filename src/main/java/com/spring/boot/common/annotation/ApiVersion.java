package com.spring.boot.common.annotation;

import java.lang.annotation.*;

/**
 * @author yuderen
 * @version 2019/8/2 14:42
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiVersion {

    int value() default 1;

}
