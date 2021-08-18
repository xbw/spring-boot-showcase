package com.xbw.spring.boot.project.jaxrs.impl;

import com.xbw.spring.boot.project.jaxrs.api.JaxRsService;

import javax.ws.rs.Path;

@Path("/sayHello2")
public class JaxRsService2Impl implements JaxRsService {

    @Override
    public String sayHello(String text) {
        return "Hello " + text + ", Welcome to CXF RS Spring Boot World!";
    }

}