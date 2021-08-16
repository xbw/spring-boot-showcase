package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.project.jaxws.JaxWsService;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.SOAPBinding;

@Configuration
public class JaxWsConfig {
    public static final String WS_SERVICE_URL = "http://localhost:8080/services/jax-ws-service";

    @Autowired
    private Bus bus;

    /**
     * Use serviceName、portName、endpointInterface、targetNamespace in *Impl
     * http://host:port/${cxf.path}/jax-ws-service?wsdl
     * @return
     */
    @Bean
    public Endpoint endpoint(@Qualifier("jaxWsService11Impl") JaxWsService jaxWsService) {
        EndpointImpl endpoint = new EndpointImpl(bus, jaxWsService);
        endpoint.publish("/jax-ws-service");
        return endpoint;
    }

    /**
     * http://host:port/${cxf.path}/JaxWsService?wsdl
     * @return
     */
    @Bean
    public Server wsServer(@Qualifier("jaxWsService12Impl") JaxWsService jaxWsService) {
        JaxWsServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
        serverFactory.setBus(bus);
        serverFactory.setServiceClass(JaxWsService.class);
        serverFactory.setAddress("/JaxWsService");
        serverFactory.setServiceBean(jaxWsService);

        serverFactory.setBindingId(SOAPBinding.SOAP12HTTP_MTOM_BINDING);
        return serverFactory.create();
    }

    @Bean
    public JaxWsProxyFactoryBean proxyService() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setAddress(WS_SERVICE_URL);
        return proxyFactory;
    }

    @Bean
    public JaxWsProxyFactoryBean jaxWsProxyFactory() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setBus(bus);
        return proxyFactory;
    }

    @Bean
    public JaxWsDynamicClientFactory jaxWsDynamicClientFactory() {
        return JaxWsDynamicClientFactory.newInstance();
    }
}