package com.spring.boot.common.annotation.validation;


import com.spring.boot.common.exception.BaseException;

/**
 * @author yuderen
 * @version 2018/8/25 15:25
 */
public class ValidationException extends BaseException {

    public ValidationException(String code, String message) {
        super(code, message);
    }

}
