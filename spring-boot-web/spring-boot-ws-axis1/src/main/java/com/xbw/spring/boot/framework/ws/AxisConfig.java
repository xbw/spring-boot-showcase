package com.xbw.spring.boot.framework.ws;

import org.apache.axis.transport.http.AxisServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 */
@Configuration
public class AxisConfig {

    @Bean
    public ServletRegistrationBean<AxisServlet> axisServlet() {
        ServletRegistrationBean<AxisServlet> servlet = new ServletRegistrationBean<>();
        servlet.setServlet(new AxisServlet());
        servlet.addUrlMappings("/services/*");
        servlet.setLoadOnStartup(1);
        servlet.setName("AxisServlet");
        return servlet;
    }

}