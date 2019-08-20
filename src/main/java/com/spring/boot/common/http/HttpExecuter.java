package com.spring.boot.common.http;


import com.spring.boot.common.http.common.HttpRequest;
import com.spring.boot.common.http.common.HttpResponse;

/**
 * author yuderen
 * version 2018/8/7 11:08
 */
public interface HttpExecuter {

    HttpResponse executeHttpRequest(HttpRequest httpRequest) throws Exception;

    void close();

}
