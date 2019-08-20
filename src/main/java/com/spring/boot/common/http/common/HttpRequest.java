package com.spring.boot.common.http.common;

import java.util.HashMap;
import java.util.Map;

/**
 * author yuderen
 * version 2018/8/7 11:11
 */
public class HttpRequest {

    private String url;
    private Map<String,String> params = new HashMap();
    private Map<String,String> heardParams = new HashMap();
    private Object jsonObject;
    private Boolean jsonRequest = false;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void addParam(String paramName, String paramValue) {
        this.params.put(paramName, paramValue);
    }

    public Map<String, String> getHeardParams() {
        return heardParams;
    }

    public void addHeardParams(Map<String, String> heardParams) {
        this.heardParams = heardParams;
    }

    public Object getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Boolean getJsonRequest() {
        return jsonRequest;
    }

    public void setJsonRequest(Boolean jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

}
