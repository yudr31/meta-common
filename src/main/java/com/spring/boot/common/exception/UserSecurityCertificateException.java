package com.spring.boot.common.exception;

/**
 * 用户安全认证相关异常类
 * @author yuderen
 * @version 2019/7/12 14:59
 */
public class UserSecurityCertificateException extends RuntimeException {

    private static final long serialVersionUID = -3118901891866592748L;

    public UserSecurityCertificateException() {
    }

    public UserSecurityCertificateException(String message) {
        super(message);
    }

    public UserSecurityCertificateException(Throwable cause) {
        super(cause);
    }

    public UserSecurityCertificateException(String message, Throwable cause) {
        super(message, cause);
    }

}
