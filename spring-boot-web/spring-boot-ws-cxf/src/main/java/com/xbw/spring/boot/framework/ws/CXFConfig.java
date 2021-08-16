package com.xbw.spring.boot.framework.ws;

import org.apache.cxf.Bus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * https://cxf.apache.org/docs/springboot.html
 * @author xbw
 * @see org.apache.cxf.Bus
 * @see org.apache.cxf.bus.spring.SpringBus
 * @see org.apache.cxf.transport.servlet.CXFServlet
 * @see org.apache.cxf.spring.boot.autoconfigure.CxfAutoConfiguration
 * @see org.apache.cxf.spring.boot.autoconfigure.CxfProperties
 */
@Configuration
public class CXFConfig implements InitializingBean {

    @Autowired
    private Bus bus;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bus: " + bus.getClass());
    }
}