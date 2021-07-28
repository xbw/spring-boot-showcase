package com.xbw.spring.boot.framework.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Order: Constructor >> @Autowired >> @PostConstruct
 * @author xbw
 */
@Component
public class PostConstructBean {

    @Autowired
    private Environment environment;

    // Order 1
    public PostConstructBean() {
        System.out.printf("The %s use Constructor start to initialize ...%n", this.getClass().getSimpleName());
    }

    // Order 3
    @PostConstruct
    private void init() {
        System.out.printf("The %s use @PostConstruct initialized.%n", this.getClass().getSimpleName());
        System.out.printf("Environment -> %s%n", environment.getClass());
    }
}
