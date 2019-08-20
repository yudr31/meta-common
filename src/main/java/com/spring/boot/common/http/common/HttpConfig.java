package com.spring.boot.common.http.common;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * author yuderen
 * version 2018/8/7 11:10
 */
@Data
@Component
public class HttpConfig {

    private String formMethod = "get";
    private String charsetType = "UTF-8";
    private Integer maxTotalConnections = 300;
    private Integer maxRouteConnections = 100;
    private Integer getConnectionWaitTimeOut = 1000;
    private Integer connectTimeout = 30000;
    private Integer readTimeout = 30000;

}
