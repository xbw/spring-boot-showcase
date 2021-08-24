package com.xbw.spring.boot;

import com.xbw.spring.boot.project.jaxws.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;

/**
 * @author xbw
 */
public class JaxWsServerMain {
    public static final String HELLO_SERVICE = "http://localhost:8080/services/hello-service";

    public static void main(String[] args) {
        // http://localhost:8080/services/hello-service?wsdl
        Endpoint.publish(HELLO_SERVICE, new HelloServiceImpl());
        //Endpoint.publish(HELLO_SERVICE, new HelloService12Impl());
    }
}
