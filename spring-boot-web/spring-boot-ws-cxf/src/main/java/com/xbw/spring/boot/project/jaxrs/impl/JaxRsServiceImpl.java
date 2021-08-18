package com.xbw.spring.boot.project.jaxrs.impl;

import com.xbw.spring.boot.project.jaxrs.api.JaxRsService;

public class JaxRsServiceImpl implements JaxRsService {

    @Override
    public String sayHello(String text) {
        return "Hello " + text;
    }

}