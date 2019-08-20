package com.spring.boot.common.annotation.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuderen
 * @version 2018/9/19 14:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PermissionLimit {

    String menuLink();

    PermissionType permissionType() default PermissionType.QUERY;

}
