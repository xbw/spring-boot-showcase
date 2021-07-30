package com.xbw.spring.boot.aop;

import com.xbw.spring.boot.project.service.AopService;
import com.xbw.spring.boot.project.service.BaseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xbw
 */
@SpringBootTest
public class AopTests {
    @Autowired
    AopService aopService;
    @Autowired
    BaseServiceImpl baseService;
    @Autowired
    DefaultPointcutAdvisor pointcutAdvisor;

    @Test
    void exception() {
        Assertions.assertThrows(RuntimeException.class, () -> aopService.exception());
        Assertions.assertThrows(RuntimeException.class, () -> baseService.exception());
    }

    @Test
    void hello() {
        Assertions.assertEquals("Hello AOP", aopService.hello());
        Assertions.assertEquals("Hello World", baseService.hello());
    }

    @Test
    void helloParam() {
        Assertions.assertEquals("Hello JUnit 5", aopService.hello("JUnit 5"));
    }

    @Test
    void annotation() {
        aopService.annotation();
        baseService.annotation();
    }
}
