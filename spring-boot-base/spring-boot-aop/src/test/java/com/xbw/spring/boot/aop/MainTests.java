package com.xbw.spring.boot.aop;

import com.xbw.spring.boot.project.main.DebugBeforeAdvice;
import com.xbw.spring.boot.project.main.DebugInterceptor;
import com.xbw.spring.boot.project.main.DebugStaticPointcut;
import com.xbw.spring.boot.project.service.AopService;
import com.xbw.spring.boot.project.service.BaseServiceImpl;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class MainTests {
    AopService aopService;

    @BeforeEach
    void setUp() {
        aopService = new AopService();
    }

    @Test
    void hello() {
        aopService.hello();
    }

    @Test
    void advice() {
        Pointcut pointcut = new DebugStaticPointcut();
        Advice advice = new DebugBeforeAdvice();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(new AopService());
        AopService proxy = (AopService) factory.getProxy();
        proxy.hello();
    }

    @Test
    void interceptor() {
        Pointcut pointcut = new DebugStaticPointcut();
        MethodInterceptor interceptor = new DebugInterceptor();
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, interceptor);

        ProxyFactory factory = new ProxyFactory();
        factory.addAdvisor(advisor);
        factory.setTarget(new BaseServiceImpl());
        BaseServiceImpl proxy = (BaseServiceImpl) factory.getProxy();
        proxy.hello();
    }
}
