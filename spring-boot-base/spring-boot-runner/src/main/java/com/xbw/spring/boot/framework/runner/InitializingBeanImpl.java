package com.xbw.spring.boot.framework.runner;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitializingBeanImpl implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("The InitializingBeanImpl use InitializingBean start to initialize ...");
        new Thread(() -> {
            throw new RuntimeException();// Safe mode
        }).start();
    }
}
