package com.xbw.spring.boot.framework.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ApplicationRunner is ahead of CommandLineRunner by default
 * @author xbw
 * @see org.springframework.boot.SpringApplication#callRunners(ApplicationContext, ApplicationArguments)
 */
//@org.springframework.core.annotation.Order(1)
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("The CommandLineRunnerImpl use CommandLineRunner start to initialize ...");
        new Thread(() -> {
            throw new RuntimeException();// Safe mode
        }).start();
    }
}