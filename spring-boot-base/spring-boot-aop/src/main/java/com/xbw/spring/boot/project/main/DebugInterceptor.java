package com.xbw.spring.boot.project.main;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-api-advice-around
 */
public class DebugInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
//        System.out.println("Before: invocation=[" + invocation + "]");
        System.out.printf("DebugInterceptor >> Class: %s, Method: %s%n", invocation.getThis().getClass().getSimpleName(), invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("Invocation returned");
        return retVal;
    }
}