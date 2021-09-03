package com.xbw.spring.boot.framework.config;

import org.codehaus.xfire.transport.http.XFireConfigurableServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xbw
 */
@Configuration
public class WSConfig {

    @Bean
    public ServletRegistrationBean<XFireConfigurableServlet> xFireConfigurableServlet() {
        ServletRegistrationBean<XFireConfigurableServlet> servlet = new ServletRegistrationBean<>();
        servlet.setServlet(new XFireConfigurableServlet());
        servlet.addUrlMappings("/servlet/XFireServlet/*");
        servlet.addUrlMappings("/services/*");
        servlet.setLoadOnStartup(1);
        servlet.setName("XFireConfigurableServlet");
        return servlet;
    }
}