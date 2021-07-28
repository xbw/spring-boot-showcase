package com.xbw.spring.boot.framework.runner;

/**
 * @author xbw
 */
public class InitMethodBean {

    public void init() {
        System.out.println("The InitMethodBean use @Bean initMethod initialized.");
    }
}

