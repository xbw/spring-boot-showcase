package com.xbw.spring.boot.project.aop;

import com.xbw.spring.boot.framework.aspectj.Aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-at-aspectj
 * In Spring AOP, aspects themselves cannot be the targets of advice from other aspects.
 * The @Aspect annotation on a class marks it as an aspect and, hence, excludes it from auto-proxying.
 * @author xbw
 */
@Aspect
//@Component
public class ServiceAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
    @Pointcut("execution(* com.xbw..service..*Service.*(..))")
    public void pointcut() {
    }

    @Pointcut("target(com.xbw.spring.boot.project.service.BaseService)")
    public void pointcutTarget() {
    }

    @Pointcut("within(com.xbw..service..*Service)")
    public void pointcutWithin() {
    }

    @Before("pointcut()")
    public void before(JoinPoint jp) {
        logger.info("@Before JoinPoint: {}", jp.toShortString());
    }

    @Before("pointcut() && args(param,..)")
    public void beforeParameter(JoinPoint jp, String param) {
        logger.info("@Before JoinPoint: {}, Parameter: {}", jp.toShortString(), param);
    }

    @Before("@annotation(aop)")
    public void beforeAop(JoinPoint jp, Aop aop) {
        logger.info("@Before JoinPoint: {}, Aop: {}", jp.toShortString(), aop.annotationType());
    }

    @Before("@annotation(aop)")
    public void beforeAnnotation(JoinPoint jp, Aop aop) {
        logger.info("@Before JoinPoint: {}, Annotation: {}", jp.toShortString(), aop.annotationType());
    }

    @AfterThrowing(value = "pointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint jp, Exception ex) {
        logger.info("@AfterThrowing JoinPoint: {}, Exception: {}", jp.toShortString(), ex.getClass());
    }

    @AfterReturning(value = "pointcut()", returning = "retVal")
    public void afterReturning(JoinPoint jp, Object retVal) {
        logger.info("@AfterReturning JoinPoint: {}, Returning: {}", jp.toShortString(), retVal);
    }

    @After("pointcut()")
    public void after(JoinPoint jp) {
        logger.info("@After JoinPoint: {}", jp.toShortString());
    }

    /**
     * https://www.eclipse.org/aspectj/doc/released/runtime-api/org/aspectj/lang/JoinPoint.html
     * Any advice method may declare, as its first parameter, a parameter of type {@link org.aspectj.lang.JoinPoint}
     * Note that around advice is required to declare a first parameter of type {@link org.aspectj.lang.ProceedingJoinPoint}, which is a subclass of JoinPoint.
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("@Around: execution");
        return getObject(pjp);
    }

    @Around("pointcutTarget()")
    public Object aroundTarget(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("@Around: target");
        return getObject(pjp);
    }

    @Around("pointcutWithin()")
    public Object aroundWithin(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("@Around: within");
        return getObject(pjp);
    }

    private Object getObject(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            logger.info("@Around JoinPoint: {}", pjp.toShortString());
            logger.info("@Around before...");
            sw.start(pjp.getSignature().getName());
            Object retVal = pjp.proceed();
            logger.info("@Around after...");
            return retVal;
        } finally {
            sw.stop();
            logger.info("@Around: \n{}", sw.prettyPrint());
        }
    }

}
