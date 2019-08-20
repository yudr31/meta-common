package com.spring.boot.common.interceptor;


import com.spring.boot.common.bean.ResponseData;
import com.spring.boot.common.exception.BaseException;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author yuderen
 * @version 2018/8/27 10:59
 */
@Aspect
@Order(10)
@Component
public class SystemExceptionHandler {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(public * com.spring.boot.*.controller.*.*(..)) ")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object initBeanData(ProceedingJoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            Class<?> clazz = joinPoint.getTarget().getClass();
            Method method = clazz.getMethod(signature.getName(),signature.getParameterTypes());
            initRequestBody(joinPoint, method);
            return doAround(joinPoint);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return new ResponseData("-1", "系统错误！");
    }

    private void initRequestBody(ProceedingJoinPoint joinPoint, Method method){
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (ArrayUtils.isNotEmpty(args) || ArrayUtils.isNotEmpty(parameterAnnotations)){
            return;
        }
        for (int i = 0; i < args.length; i++){
            Annotation[] parameterAnnotation = parameterAnnotations[i];
            if (ArrayUtils.isNotEmpty(parameterAnnotation)){
                for (Annotation annotation : parameterAnnotation){
                    if (annotation instanceof RequestBody){
                        System.out.println(args[i]);
                    }
                }
            }
        }
    }

//    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint call) {
        try {
            Object result = call.proceed();
            return result;
        } catch (Throwable e) {
            logger.error("系统错误", e);
            if (e instanceof BaseException) {
                BaseException ex = (BaseException) e;
                return new ResponseData(ex.getCode() + "", ex.getMessage());
            }
            return new ResponseData("-1", "系统错误！");
        }
    }

}
