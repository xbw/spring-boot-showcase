package com.xbw.spring.boot.project.axis;

import org.springframework.stereotype.Component;

/**
 * http://localhost:8080/services/HelloService
 * http://localhost:8080/services/HelloService?wsdl
 */
@Component
public class HelloService {
    public String sayHello(String text) {
        text = "Hello " + text;
        System.out.println("sayHello: " + text);
        return text;
    }
}