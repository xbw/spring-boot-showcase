package com.xbw.spring.boot.framework.config;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.spring.SpringProcessEngineServicesConfiguration;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 * @see org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration
 * @see org.camunda.bpm.engine.spring.SpringProcessEngineServicesConfiguration
 * @see org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration
 * @see org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties
 */
@Configuration
public class CamundaConfig implements InitializingBean {
    @Autowired
    CamundaBpmProperties properties;
    @Autowired
    SpringProcessEngineServicesConfiguration configuration;
    @Autowired
    ProcessEngine processEngine;


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AdminUser: " + properties.getAdminUser().getPassword());
    }
}
