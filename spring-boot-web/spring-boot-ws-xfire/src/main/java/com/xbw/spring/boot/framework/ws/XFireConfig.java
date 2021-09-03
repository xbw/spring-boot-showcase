package com.xbw.spring.boot.framework.ws;

import org.codehaus.xfire.XFire;
import org.codehaus.xfire.annotations.jsr181.Jsr181WebAnnotations;
import org.codehaus.xfire.spring.XFireSpringServlet;
import org.codehaus.xfire.spring.remoting.Jsr181HandlerMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author xbw
 */
@Configuration
@ImportResource("classpath:org/codehaus/xfire/spring/xfire.xml")
public class XFireConfig {

    @Bean
    public ServletRegistrationBean<XFireSpringServlet> xFireSpringServlet() {
        ServletRegistrationBean<XFireSpringServlet> servlet = new ServletRegistrationBean<>();
        servlet.setServlet(new XFireSpringServlet());
        servlet.addUrlMappings("/servlet/XFireServlet/*");
        servlet.addUrlMappings("/services/*");
        servlet.setLoadOnStartup(1);
        servlet.setName("XFireSpringServlet");
        return servlet;
    }

    @Bean
    public Jsr181HandlerMapping jsr181HandlerMapping(XFire xFire, Jsr181WebAnnotations webAnnotations) {
        Jsr181HandlerMapping mapping = new Jsr181HandlerMapping();
        mapping.setWebAnnotations(webAnnotations);
        mapping.setXfire(xFire);
        return mapping;
    }

    @Bean
    public Jsr181WebAnnotations webAnnotations() {
        return new Jsr181WebAnnotations();
    }
}