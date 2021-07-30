package com.xbw.spring.boot.project.main;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

public class DebugStaticPointcut extends StaticMethodMatcherPointcut {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        // return true if custom criteria match
        return ("hello".equals(method.getName()));
    }

//    @Override
//    public ClassFilter getClassFilter() {
//        return clazz -> clazz.equals(AopService.class);
//    }
}