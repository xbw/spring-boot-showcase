package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.CXFMain;
import com.xbw.spring.boot.project.jaxws.JaxWsService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author xbw
 */
public class CXFMainTests {

    @Test
    void proxyFactory() {
        JaxWsProxyFactoryBean proxyFactory = new JaxWsProxyFactoryBean();
        proxyFactory.setServiceClass(JaxWsService.class);
        proxyFactory.setAddress(CXFMain.WS_SERVICE_URL);

        JaxWsService jaxWsService = proxyFactory.create(JaxWsService.class);
        Assertions.assertEquals("Hello ProxyFactory", jaxWsService.sayHello("ProxyFactory"));
    }

    @Test
    void dynamicClientFactory() throws Exception {
        JaxWsDynamicClientFactory dynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = dynamicClientFactory.createClient(CXFMain.WS_SERVICE_URL + "?wsdl");
        Object[] obj = client.invoke("sayHello", "DynamicClientFactory");
        Assertions.assertEquals("Hello DynamicClientFactory", obj[0]);
    }
}
