package com.xbw.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.webservices.WebServicesProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


/**
 * https://spring.io/guides/gs/producing-web-service/
 * https://spring.io/guides/gs/consuming-web-service/
 * @see org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration
 * @see org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration
 * @see org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
 * @see org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition
 * @see org.springframework.xml.xsd.SimpleXsdSchema
 * @see com.sun.xml.ws.transport.http.servlet.WSServlet
 */
@Configuration
//@org.springframework.ws.config.annotation.EnableWs //if enabled, WebServicesAutoConfiguration is inactive
public class WSConfig extends WsConfigurerAdapter {

    /**
     * @param applicationContext
     * @return
     * @see org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration#messageDispatcherServlet(ApplicationContext, WebServicesProperties)
     */
    //@Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/services/*", "*.wsdl");
    }

    /**
     * http://host:port/${spring.webservices.path}/default/default.wsdl will now display the generated WSDL.
     * @param schema
     * @return
     */
    @Bean(name = "default")
    public DefaultWsdl11Definition defaultWsdl11Definition(@Qualifier("serviceSchema") XsdSchema schema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("DefaultService");
        wsdl.setServiceName("DefaultWS");
        wsdl.setLocationUri("/default/");
        wsdl.setSchema(schema);
        return wsdl;
    }

    /**
     * http://host:port/${spring.webservices.path}/hello.wsdl will now display the generated WSDL.
     * @param schema
     * @return
     */
    @Bean(name = "hello")
    public DefaultWsdl11Definition helloWsdl11Definition(@Qualifier("serviceSchema") XsdSchema schema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("HelloService");
        wsdl11Definition.setServiceName("HelloWS");
        wsdl11Definition.setSchema(schema);
        return wsdl11Definition;
    }

    @Bean(name = "countries")
    public DefaultWsdl11Definition countriesWsdl11Definition(XsdSchema countriesSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CountriesPort");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://boot.spring.xbw.com/project/services/country");
        wsdl11Definition.setSchema(countriesSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/xsd/countries.xsd"));
    }

    @Bean
    public XsdSchema serviceSchema() {
        return new SimpleXsdSchema(new ClassPathResource("schema/xsd/HelloService.xsd"));
    }
}