package com.spring.boot.common.config;

import com.spring.boot.common.handler.CustomRequestMappingHandlerMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author yuderen
 * @version 2019/8/2 15:02
 */
//@Slf4j
//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public RequestMappingHandlerMapping requestMappingHandlerMapping(){
        return new CustomRequestMappingHandlerMapping();
    }
}