package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.project.jaxrs.impl.JaxRsService2Impl;
import com.xbw.spring.boot.project.jaxrs.impl.JaxRsServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.openapi.OpenApiFeature;
import org.apache.cxf.metrics.MetricsFeature;
import org.apache.cxf.metrics.MetricsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author xbw
 */
@Configuration
public class JaxRsConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private MetricsProvider metricsProvider;

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean serverFactory = new JAXRSServerFactoryBean();
        serverFactory.setBus(bus);
        //serverFactory.setServiceBean(new JaxRsServiceImpl());
        serverFactory.setServiceBeans(Arrays.asList(new JaxRsServiceImpl(), new JaxRsService2Impl()));
        serverFactory.setAddress("/JaxRsService");
        serverFactory.setFeatures(Arrays.asList(createOpenApiFeature(), metricsFeature()));
        return serverFactory.create();
    }

    /**
     * https://cxf.apache.org/docs/openapifeature.html
     * @return
     */
    @Bean
    public OpenApiFeature createOpenApiFeature() {
        final OpenApiFeature openApiFeature = new OpenApiFeature();
        openApiFeature.setPrettyPrint(true);
        openApiFeature.setTitle("Spring Boot CXF REST Application");
        openApiFeature.setContactName("The Apache CXF Team");
        openApiFeature.setDescription("This sample project demonstrates how to use CXF JAX-RS services"
                + " with Spring Boot. This demo has two JAX-RS class resources being"
                + " deployed in a single JAX-RS endpoint.");
        openApiFeature.setVersion("1.0.0");
        return openApiFeature;
    }

    @Bean
    public MetricsFeature metricsFeature() {
        return new MetricsFeature(metricsProvider);
    }

}