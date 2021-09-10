package com.xbw.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.jaxws.SimpleJaxWsServiceExporter;

@ConditionalOnMissingBean(WSConfig.class)
@Configuration
public class JaxWsConfig {
    @Value("${spring.webservices.host:127.1}")
    private String host;
    @Value("${spring.webservices.port:8081}")
    private String port;
    @Value("${spring.webservices.path:/services}")
    private String services;


    @Bean
    public SimpleJaxWsServiceExporter jaxWsServiceExporter() {
        SimpleJaxWsServiceExporter exporter = new SimpleJaxWsServiceExporter();
        String address = "http://" + host + port + services + "/";
        System.out.println(address);
        exporter.setBaseAddress(address);
        return exporter;
    }
}
