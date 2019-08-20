package com.spring.boot.common.interceptor;

import com.spring.boot.common.util.IdGenerator;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuderen
 * @version 2019/7/30 15:42
 */
@Component
public class LogTraceInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // "traceId"
        String traceId = IdGenerator.generateUUID();
        MDC.put("traceId", traceId);
        return true;
    }

}
