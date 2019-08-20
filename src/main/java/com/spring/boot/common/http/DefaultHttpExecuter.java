package com.spring.boot.common.http;



import com.spring.boot.common.http.common.HttpConfig;
import com.spring.boot.common.http.common.HttpRequest;
import com.spring.boot.common.http.common.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * author yuderen
 * version 2018/8/7 11:09
 */
@Service
public class DefaultHttpExecuter implements HttpExecuter {

    private Logger logger = LoggerFactory.getLogger(DefaultHttpExecuter.class);
    private CloseableHttpClient httpClient;
    @Autowired
    private HttpConfig httpConfig;

    public HttpResponse executeHttpRequest(HttpRequest httpRequest) {
        HttpResponse response = new HttpResponse();
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = this.getHttpClient().execute(this.getHttpRequest(httpRequest));
            response.setCharset(this.httpConfig.getCharsetType());
            response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
            response.setResponseMsg(EntityUtils.toString(httpResponse.getEntity(),this.httpConfig.getCharsetType()));
        } catch (Exception e){

        } finally {
            if (null != httpResponse){
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }

    public void close() {
        CloseableHttpClient httpClient = this.getHttpClient();
        if (null != httpClient){
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.error("httpClient关闭异常");
            }
        }
    }

    public CloseableHttpClient getHttpClient(){
        if (null == this.httpClient){
            synchronized (this){
                if (null == this.httpClient){
                    try {
                        this.httpClient = this.createCloseableHttpClient(this.httpConfig);
                    } catch (Exception e) {
                        logger.error("建立HTTPClient连接异常:{}",e);
                    }
                }
            }
        }
        return this.httpClient;
    }

    public CloseableHttpClient createCloseableHttpClient(HttpConfig httpConfig) throws Exception {
        SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore)null,new TrustStrategy(){
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(1, TimeUnit.MINUTES);
        connectionManager.setMaxTotal(httpConfig.getMaxTotalConnections());
        connectionManager.setDefaultMaxPerRoute(httpConfig.getMaxRouteConnections());
        return HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).setConnectionManager(connectionManager).build();
    }

    public HttpRequestBase getHttpRequest(HttpRequest httpRequest){
        HttpRequestBase httpRequestBase = null;
        if ("post".equalsIgnoreCase(this.httpConfig.getFormMethod())){
            httpRequestBase = getHttpRequestOfPost(httpRequest);
        } else if ("get".equalsIgnoreCase(this.httpConfig.getFormMethod())){
            httpRequestBase = getHttpRequestOfGet(httpRequest);
        }

        if (null != httpRequestBase){
            httpRequestBase.setConfig(
                    RequestConfig.custom().setConnectionRequestTimeout(this.httpConfig.getConnectTimeout())
                            .setSocketTimeout(this.httpConfig.getReadTimeout()).build()
            );
        }
        return httpRequestBase;
    }

    public HttpRequestBase getHttpRequestOfPost(HttpRequest httpRequest){
        HttpPost httpPost = new HttpPost(httpRequest.getUrl());
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePairList(httpRequest.getParams()), this.httpConfig.getCharsetType()));
        } catch (UnsupportedEncodingException e) {
            this.logger.error("httpPost设置出现异常");
            httpPost = null;
        }
        return httpPost;
    }

    public HttpRequestBase getHttpRequestOfGet(HttpRequest httpRequest){
        HttpGet httpGet = null;
        try {
            String url = String.format("%s?%s",httpRequest.getUrl(),getParamStr(httpRequest.getParams(),this.httpConfig.getCharsetType()));
            httpGet = new HttpGet(url);
        } catch (UnsupportedEncodingException e) {
            this.logger.error("httpGet设置出现异常");
        }
        return httpGet;
    }

    public List<NameValuePair> getNameValuePairList(Map<String, String> params){
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        if (CollectionUtils.isEmpty(params)){
            return nameValuePairList;
        }

        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String, String>) iterator.next();
            nameValuePairList.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        return nameValuePairList;
    }

    public String getParamStr(Map<String,String> params,String charset) throws UnsupportedEncodingException {
        String paramStr = "";
        if (CollectionUtils.isEmpty(params)){
            return paramStr;
        }

        Iterator iterator = params.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)iterator.next();
            paramStr = paramStr + String.format("%s=%s&", URLEncoder.encode(entry.getKey(), charset),URLEncoder.encode(entry.getValue(), charset));
        }
        paramStr = paramStr.substring(0, paramStr.length() - 1);
        return paramStr;
    }

    public DefaultHttpExecuter(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }

}
