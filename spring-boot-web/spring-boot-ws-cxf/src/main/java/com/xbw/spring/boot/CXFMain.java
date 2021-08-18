package com.xbw.spring.boot;

import com.xbw.spring.boot.project.jaxrs.impl.JaxRsService2Impl;
import com.xbw.spring.boot.project.jaxrs.impl.JaxRsServiceImpl;
import com.xbw.spring.boot.project.jaxws.JaxWsService;
import com.xbw.spring.boot.project.jaxws.impl.JaxWsService11Impl;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import java.util.Arrays;

/**
 * Caused by: java.io.IOException: Cannot find any registered HttpDestinationFactory from the Bus.
 * need org.apache.cxf:cxf-rt-transports-http-jetty
 * or cxf-rt-transports-http-undertow since 3.2.0
 * @author xbw
 */
public class CXFMain {
    public static final String WS_SERVICE_URL = "http://localhost:8080/services/JaxWsService";
    public static final String RS_SERVICE_URL = "http://localhost:8080/services/JaxRsService";

    public static void main(String[] args) {
        soap();
        rest();
    }

    /**
     * ${SERVICE_WS_URL}?wsdl
     */
    private static void soap() {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(JaxWsService.class);
        factory.setAddress(WS_SERVICE_URL);
        factory.setServiceBean(new JaxWsService11Impl());
        factory.create();
    }

    /**
     * ${SERVICE_RS_URL}/sayHello/{text}
     */
    private static void rest() {
        JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
        factory.setAddress(RS_SERVICE_URL);
        //factory.setServiceBean(new JaxRsServiceImpl());
        factory.setServiceBeans(Arrays.asList(new JaxRsServiceImpl(), new JaxRsService2Impl()));
        factory.create();
    }

}
