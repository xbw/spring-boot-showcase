package com.xbw.spring.boot.framework.ws;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.service.ServiceFactory;
import org.codehaus.xfire.spring.remoting.XFireExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 */
@Configuration
public class XFireExporterConfig {
    @Autowired
    XFire xFire;
    @Autowired
    @Qualifier("xfire.serviceFactory")
    ServiceFactory serviceFactory;

    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping2(XFireExporter exporterService) {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        Map<String, Object> map = new HashMap<>();
        map.put("/ExporterService.ws", exporterService);
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public XFireExporter exporterService(com.xbw.spring.boot.project.xfire.ExporterService service) {
        XFireExporter exporter = new XFireExporter();
        exporter.setServiceFactory(serviceFactory);
        exporter.setXfire(xFire);
        exporter.setServiceBean(service);
        exporter.setServiceClass(com.xbw.spring.boot.project.xfire.ExporterService.class);
        return exporter;
    }
}
