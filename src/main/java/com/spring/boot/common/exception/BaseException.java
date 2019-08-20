package com.spring.boot.common.exception;

/**
 * @author yuderen
 * @version 2018/8/27 10:54
 */
public class BaseException extends RuntimeException{

    private String code;
    private String message;

    public BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
