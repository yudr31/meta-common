package com.spring.boot.common.http.common;

/**
 * author yuderen
 * version 2018/8/7 11:12
 */
public class HttpResponse {

    private String charset;
    private int statusCode;
    private String responseMsg;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    @Override
    public String toString() {
        return "HttpResponse{charset='" + this.charset + '\'' + ", statusCode=" + this.statusCode + ", responseMsg='" + this.responseMsg + '\'' + '}';
    }

}
