package com.sss.java_exerise.config;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;


@Aspect
@Configuration
public class AopAspectJ {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // Pointcut that matches all controller methods
    @Pointcut("execution(* com.sss.java_exerise.controller.*.*(..))")
    public void controllerMethods() {}

    // Before the controller method is called (logging request)
    @Before("controllerMethods()")
    public void logRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("Request Method: {}, Request URI: {}", request.getMethod(), request.getRequestURI());
    }

    // After the controller method is executed (logging response)
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logResponse(Object result) {
        // You can also log the response status or any data you want
        logger.info("Response: {}", result);
    }
}
