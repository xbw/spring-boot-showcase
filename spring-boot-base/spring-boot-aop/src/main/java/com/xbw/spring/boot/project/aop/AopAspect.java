package com.xbw.spring.boot.project.aop;

import com.xbw.spring.boot.framework.aspectj.Aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author xbw
 */
@Aspect
//@Component
public class AopAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.pointcut()")
    public void annotation(JoinPoint jp) {
        Class<? extends Annotation> clazz = Aop.class;
        Class<? extends Object> targetClass = jp.getTarget().getClass();
        if (!targetClass.isAnnotationPresent(clazz)) {
            Method method = ((MethodSignature) jp.getSignature()).getMethod();
            if (method.isAnnotationPresent(clazz)) {
                System.out.println("Method annotation: " + method.getAnnotation(clazz));
            }
        } else {
            System.out.println("Class annotation: " + targetClass.getAnnotation(clazz));
        }
    }

    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.pointcut()")
    public void before(JoinPoint jp) {
        logger.info("@Before JoinPoint: {}, Annotation", jp.toShortString());
    }

    /**
     * Using the 'argNames' attribute is a little clumsy, so if the 'argNames' attribute has not been specified,
     * Spring AOP looks at the debug information for the class and tries to determine the parameter names from the local variable table.
     * @param jp
     * @param aop
     */
    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.pointcutParam(aop)")
    public void before(JoinPoint jp, Aop aop) {
        if (ObjectUtils.isEmpty(aop)) {
            logger.warn("@Before JoinPoint: {}, Annotation: {}", jp.toShortString(), aop);
        } else {
            logger.info("@Before JoinPoint: {}, Annotation: {}", jp.toShortString(), aop.annotationType().getSimpleName());
        }
    }

    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.clazz()")
    public void beforeClazz(JoinPoint jp) {
        logger.info("@Before JoinPoint: {}, Annotated class", jp.toShortString());
    }

    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.clazzParam(aop)")
    public void beforeClazz(JoinPoint jp, Aop aop) {
        logger.info("@Before JoinPoint: {}, Annotated class: {}", jp.toShortString(), aop.annotationType().getSimpleName());
    }

    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.method()")
    public void beforeMethod(JoinPoint jp) {
        logger.info("@Before JoinPoint: {}, Annotated method", jp.toShortString());
    }

    @Before("com.xbw.spring.boot.framework.aspectj.AopPointcuts.methodParam(aop)")
    public void beforeMethod(JoinPoint jp, Aop aop) {
        logger.info("@Before JoinPoint: {}, Annotated method: {}", jp.toShortString(), aop.annotationType().getSimpleName());
    }
}
