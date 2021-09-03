package com.xbw.spring.boot.framework.ws;

import com.xbw.spring.boot.project.pojo.WeatherService;
import org.codehaus.xfire.spring.ServiceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 */
@Configuration
public class ServiceBeanConfig {

    @Bean
    public ServiceBean serviceBean(com.xbw.spring.boot.project.xfire.HelloService service) {
        ServiceBean serviceBean = new ServiceBean();
        serviceBean.setServiceBean(service);
        serviceBean.setServiceClass(com.xbw.spring.boot.project.xfire.HelloService.class);
        serviceBean.setName("ServiceBean");
        return serviceBean;
    }

    @Bean
    public ServiceBean weatherServiceBean() {
        ServiceBean serviceBean = new ServiceBean();
        serviceBean.setServiceBean(new WeatherService());
        serviceBean.setServiceClass(WeatherService.class);
        return serviceBean;
    }
}
