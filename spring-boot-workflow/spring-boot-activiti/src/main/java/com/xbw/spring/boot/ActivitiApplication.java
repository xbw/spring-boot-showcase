package com.xbw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Caused by: java.lang.ClassNotFoundException: org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
 */
@SpringBootApplication(exclude = org.activiti.spring.boot.SecurityAutoConfiguration.class)
public class ActivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
    }

}
