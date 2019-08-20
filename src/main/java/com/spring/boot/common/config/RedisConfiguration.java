package com.spring.boot.common.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuderen
 * @version 2019/7/23 10:28
 */
@Configuration
@EnableCaching
public class RedisConfiguration extends CachingConfigurerSupport {
}
