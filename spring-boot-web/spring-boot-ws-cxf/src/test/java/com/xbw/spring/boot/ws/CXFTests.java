package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.framework.config.JaxWsConfig;
import com.xbw.spring.boot.project.jaxws.JaxWsService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xbw
 * @see org.apache.cxf.jaxrs.client.spring.JaxRsWebClientConfiguration;
 * @see org.apache.cxf.jaxrs.client.spring.JaxRsProxyClientConfiguration;
 */
@SpringBootTest
class CXFTests {
    @Autowired
    JaxWsProxyFactoryBean proxyService;
    @Autowired
    JaxWsProxyFactoryBean jaxWsProxyFactory;
    @Autowired
    JaxWsDynamicClientFactory dynamicClientFactory;

    @Test
    void proxyService() {
        JaxWsService jaxWsService = proxyService.create(JaxWsService.class);
        Assertions.assertEquals("Hello World", jaxWsService.sayHello("World"));
    }

    @Test
    void proxyFactory() {
        jaxWsProxyFactory.setAddress(JaxWsConfig.WS_SERVICE_URL);
        JaxWsService jaxWsService = jaxWsProxyFactory.create(JaxWsService.class);
        Assertions.assertEquals("Hello ProxyFactory", jaxWsService.sayHello("ProxyFactory"));
    }

    @Test
    void dynamicClientFactory() throws Exception {
        Client client = dynamicClientFactory.createClient(JaxWsConfig.WS_SERVICE_URL + "?wsdl");
        Object[] obj = client.invoke("sayHello", "DynamicClientFactory");

        Assertions.assertEquals("Hello DynamicClientFactory", obj[0]);
        System.out.println(obj[0]);
    }
}
