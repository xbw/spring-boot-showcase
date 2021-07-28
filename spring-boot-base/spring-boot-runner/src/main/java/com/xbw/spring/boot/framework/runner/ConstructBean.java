package com.xbw.spring.boot.framework.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 */
@Component
public class ConstructBean {

    private final Environment environment;

    @Autowired
    public ConstructBean(Environment environment) {
        this.environment = environment;
        System.out.println("The ConstructBean use Constructor initialized.");
    }

}
