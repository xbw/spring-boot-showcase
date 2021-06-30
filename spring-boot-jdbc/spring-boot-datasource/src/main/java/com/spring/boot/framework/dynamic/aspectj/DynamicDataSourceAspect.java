package com.spring.boot.framework.dynamic.aspectj;

import com.spring.boot.framework.dynamic.DynamicDataSourceHolder;
import com.spring.boot.framework.dynamic.annotation.DynamicDS;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Dynamic DataSource Aspect
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @Pointcut("@annotation(com.spring.boot.framework.dynamic.annotation.DynamicDS)") // only for method
    @Pointcut("@annotation(com.spring.boot.framework.dynamic.annotation.DynamicDS) || @within(com.spring.boot.framework.dynamic.annotation.DynamicDS)")
    public void dynamicDSPointCut() {

    }

    @Around("dynamicDSPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DynamicDS dynamicDS = getDynamicDS(point);

        if (null != dynamicDS) {
            DynamicDataSourceHolder.setDataSource(dynamicDS.value().name());
            logger.debug("Change to {} DataSource", dynamicDS.value());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    private DynamicDS getDynamicDS(ProceedingJoinPoint point) {
        DynamicDS dynamicDS = null;
        Class<DynamicDS> clazz = DynamicDS.class;

        Class<? extends Object> targetClass = point.getTarget().getClass();
        if (targetClass.isAnnotationPresent(clazz)) {
            dynamicDS = targetClass.getAnnotation(clazz);
        }

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
//        logger.info("signature.getMethod() -> {}", method.getName());
        if (method.isAnnotationPresent(clazz)) {
            dynamicDS = method.getAnnotation(clazz);
        }

        return dynamicDS;
    }
}
