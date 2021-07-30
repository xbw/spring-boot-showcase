package com.xbw.spring.boot.project.main;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-api-advice-before
 */
public class DebugBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.printf("DebugBeforeAdvice >> Class: %s, Method: %s%n", target.getClass().getSimpleName(), method.getName());
    }
}