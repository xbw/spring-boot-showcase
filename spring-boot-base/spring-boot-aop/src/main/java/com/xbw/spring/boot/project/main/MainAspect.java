package com.xbw.spring.boot.project.main;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * need org.codehaus.mojo:aspectj-maven-plugin
 * @author xbw
 */
@Aspect
public class MainAspect {

    @Pointcut("execution(* com.xbw.spring.boot..service..*Service.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("@Before: " + joinPoint.getClass());
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("@AfterReturning: " + joinPoint);
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("@After: " + joinPoint);
    }
}
