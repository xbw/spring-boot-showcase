package com.xbw.spring.boot.framework.ws;

import org.apache.axis2.deployment.WarBasedAxisConfigurator;
import org.apache.axis2.extensions.spring.receivers.ApplicationContextHolder;
import org.apache.axis2.transport.http.AxisServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * https://axis.apache.org/axis2/java/core/docs/spring.html
 * https://axis.apache.org/axis2/java/core/docs/json-springboot-userguide.html
 * <p>
 * https://axis.apache.org/axis2/java/core/docs/axis2config.html
 * https://axis.apache.org/axis2/java/core/docs/servlet-transport.html
 * @see org.apache.axis2.deployment.WarBasedAxisConfigurator
 */
@Configuration
public class AxisConfig {

    @Value("${axis2.services.path:/services}")
    private String services;

    @Value("${axis2.xml.path:/axis2.xml}")
    private String xmlPath;

    /**
     * org.apache.axis2:axis2-kernel:org/apache/axis2/axis2.xml
     * org.apache.axis2:axis2-kernel:org/apache/axis2/deployment/axis2_default.xml
     * @return
     * @see org.apache.axis2.deployment.DeploymentConstants.AXIS2_CONFIGURATION_XML
     * @see org.apache.axis2.deployment.DeploymentConstants.AXIS2_CONFIGURATION_RESOURCE
     */
    @Bean
    public ServletRegistrationBean<AxisServlet> axisServlet() {
        ServletRegistrationBean<AxisServlet> servlet = new ServletRegistrationBean<>();
        servlet.setServlet(new AxisServlet());
        servlet.addUrlMappings(services + "/*");
        try {
            String path = new ClassPathResource("/").getFile().getAbsolutePath();
            servlet.addInitParameter(WarBasedAxisConfigurator.PARAM_AXIS2_XML_PATH, path + xmlPath);
            servlet.addInitParameter(WarBasedAxisConfigurator.PARAM_AXIS2_REPOSITORY_PATH, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        servlet.setLoadOnStartup(1);
        return servlet;
    }

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

}