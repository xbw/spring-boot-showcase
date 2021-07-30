package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.project.main.DebugBeforeAdvice;
import com.xbw.spring.boot.project.main.DebugInterceptor;
import com.xbw.spring.boot.project.main.DebugStaticPointcut;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AspectConfig {

    @Primary
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        MethodInterceptor interceptor = new DebugInterceptor();
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.xbw..service..*.*(..))");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(interceptor);
        return advisor;
    }

    @Bean
    public DebugStaticPointcut debugStaticPointcut() {
        return new DebugStaticPointcut();
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisorBeforeAdvice(DebugStaticPointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new DebugBeforeAdvice());
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisorInterceptor(DebugStaticPointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new DebugInterceptor());
    }
}