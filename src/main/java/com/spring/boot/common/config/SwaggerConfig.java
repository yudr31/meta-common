package com.spring.boot.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author yuderen
 * @version 2018/8/24 14:05
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${eureka.client.serviceUrl.defaultZone:www.baidu.com}")
    private String url;

    @Value("${spring.application.name:applicationName}")
    private String serviceName;

    @Value("${swagger.api.description:swagger-api}")
    private String apiDescription;

    @Bean
    public Docket createRestApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(createApiInfo()).directModelSubstitute(Timestamp.class, Long.class);
        docket.select().apis(RequestHandlerSelectors.basePackage("com.spring.boot"))
                .paths(PathSelectors.any()).build();
        return docket;
    }

    private ApiInfo createApiInfo(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title(serviceName).description(apiDescription)
                .termsOfServiceUrl(url).version(String.format("1.0-%s", LocalDateTime.now()));
        return apiInfoBuilder.build();
    }

}
