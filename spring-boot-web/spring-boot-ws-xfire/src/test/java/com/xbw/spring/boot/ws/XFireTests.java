package com.xbw.spring.boot.ws;

import com.xbw.spring.boot.project.jaxws.HelloService;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

/**
 * @author xbw
 */
class XFireTests {

    @Test
    void hello() throws MalformedURLException {
        Service service = new ObjectServiceFactory().create(HelloService.class);
        String url = "http://localhost:8080//services/HelloService";
        HelloService helloService = (HelloService) new XFireProxyFactory().create(service, url);
        Assertions.assertEquals("Hello xbw", helloService.sayHello("xbw"));
    }
}
