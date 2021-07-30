package com.xbw.spring.boot.framework.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop-common-pointcuts
 */
@Aspect
public class CommonPointcuts {

    /**
     * A join point is in the web layer if the method is defined
     * in a type in the com..web package or any sub-package
     * under that.
     */
    @Pointcut("within(com..controller..*)")
    public void inWebLayer() {
    }

    /**
     * A join point is in the service layer if the method is defined
     * in a type in the com..service package or any sub-package
     * under that.
     */
    @Pointcut("within(com..service..*)")
    public void inServiceLayer() {
    }

    /**
     * A join point is in the data access layer if the method is defined
     * in a type in the com..dao package or any sub-package
     * under that.
     */
    @Pointcut("within(com..dao..*)")
    public void inDataAccessLayer() {
    }

    /**
     * A business service is the execution of any method defined on a service
     * interface. This definition assumes that interfaces are placed in the
     * "service" package, and that implementation types are in sub-packages.
     * <p>
     * If you group service interfaces by functional area (for example,
     * in packages com..abc.service and com..def.service) then
     * the pointcut expression "execution(* com...service.*.*(..))"
     * could be used instead.
     * <p>
     * Alternatively, you can write the expression using the 'bean'
     * PCD, like so "bean(*Service)". (This assumes that you have
     * named your Spring service beans in a consistent fashion.)
     */
    @Pointcut("execution(* com..service.*.*(..))")
    public void businessService() {
    }

    /**
     * A data access operation is the execution of any method defined on a
     * dao interface. This definition assumes that interfaces are placed in the
     * "dao" package, and that implementation types are in sub-packages.
     */
    @Pointcut("execution(* com..dao.*.*(..))")
    public void dataAccessOperation() {
    }

}