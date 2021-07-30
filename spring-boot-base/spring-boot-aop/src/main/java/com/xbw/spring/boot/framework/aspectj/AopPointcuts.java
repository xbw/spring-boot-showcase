package com.xbw.spring.boot.framework.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopPointcuts {

    @Pointcut("@annotation(Aop) || @within(Aop)")
    public void pointcut() {
    }

    /**
     * https://stackoverflow.com/questions/21443191/binding-annotation-objects-to-advice-body
     * @param aop
     */
//    @Pointcut(value = "@annotation(aop) || @within(aop)", argNames = "aop")
    public void pointcut(Aop aop) {
    }

    // @Pointcut(value = "@within(aop) || @annotation(aop)", argNames = "aop") // "aop" active to @annotation(aop)
    @Pointcut(value = "@annotation(aop) || @within(aop)", argNames = "aop") // "aop" active to @within(aop)
    public void pointcutParam(Aop aop) {
    }

    @Pointcut("@annotation(Aop)")
    public void method() {
    }

    @Pointcut(value = "@annotation(aop)", argNames = "aop")
    public void methodParam(Aop aop) {
    }

    @Pointcut("@within(Aop)")
    public void clazz() {
    }

    @Pointcut(value = "@within(aop)", argNames = "aop")
    public void clazzParam(Aop aop) {
    }
}
