package com.xbw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://www.baeldung.com/running-setup-logic-on-startup-in-spring
 * @author xbw
 */
@SpringBootApplication
public class RunnerApplication {

    public static void main(String[] args) {
        System.out.println("The Application to start.");
        SpringApplication.run(RunnerApplication.class, args);
        System.out.println("The Application has started.");
    }

}
