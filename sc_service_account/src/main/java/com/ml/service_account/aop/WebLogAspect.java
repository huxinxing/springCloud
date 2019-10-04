package com.ml.service_helloworld.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("execution(public * com.ml.service_account.controller..*.*(..))")
    public void webLog(){

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        log.info("WebApplication.doBefore");
        StringBuilder sbmsg = new StringBuilder();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        sbmsg.append("用户服务====");
        sbmsg.append("URL: " + request.getRequestURL().toString() + " HTTP_METHOD: " + request.getMethod() + " IP: " + request.getRemoteAddr() + " CLASS_METHOD: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName() + " ARGS: " + Arrays.toString(joinPoint.getArgs()));
        Enumeration enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()){
            String paraName = (String)enumeration.nextElement();
            sbmsg.append( " " + paraName + ": " + request.getParameter(paraName));
        }
        log.info(sbmsg.toString());
    }

    @AfterReturning("webLog()")
    public void doAfterReturning(JoinPoint joinPoint){
        log.info("WebLongAspect.doAfterReturning()");
    }


}
