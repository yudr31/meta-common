package com.spring.boot.common.exception;

/**
 * 加密解密相关异常类
 * @author yuderen
 * @version 2019/7/12 14:58
 */
public class EncryptAndDecryptException extends RuntimeException {

    private static final long serialVersionUID = -3118901891866592748L;

    public EncryptAndDecryptException() {
    }

    public EncryptAndDecryptException(String message) {
        super(message);
    }

    public EncryptAndDecryptException(Throwable cause) {
        super(cause);
    }

    public EncryptAndDecryptException(String message, Throwable cause) {
        super(message, cause);
    }

}
