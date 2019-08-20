package com.spring.boot.common.annotation.permission;

/**
 * @author yuderen
 * @version 2018/9/19 14:11
 */
public enum PermissionType {

    QUERY(1),
    CREATOR(2),
    EDITOR(4),
    DELETER(8)
    ;

    private Integer value;

    PermissionType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
