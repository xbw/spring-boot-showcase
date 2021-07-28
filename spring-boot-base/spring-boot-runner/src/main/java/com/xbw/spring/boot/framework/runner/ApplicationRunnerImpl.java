package com.xbw.spring.boot.framework.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 */
//@org.springframework.core.annotation.Order(2)
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("The ApplicationRunnerImpl use ApplicationRunner start to initialize ...");
        new Thread(() -> {
            throw new RuntimeException();// Safe mode
        }).start();
    }
}