package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.framework.activiti.UUIDIdGenerator;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.cfg.IdGenerator;
import org.activiti.engine.impl.db.DbIdGenerator;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * https://spring.io/blog/2015/03/08/getting-started-with-activiti-and-spring-boot
 * https://stackoverflow.com/questions/49024354/how-to-disable-activiti-auto-deployment-on-spring-boot-project
 * @author xbw
 * @see org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration
 * @see org.activiti.spring.boot.DataSourceProcessEngineAutoConfiguration
 * @see org.activiti.spring.boot.JpaProcessEngineAutoConfiguration
 * @see org.activiti.spring.boot.ActivitiProperties
 * @see org.activiti.spring.boot.RestApiAutoConfiguration
 */
@Configuration
public class ActivitiConfig implements InitializingBean {
    /**
     * https://www.activiti.org/5.x/userguide/#configurationRoot
     * @see org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration
     * @see org.activiti.engine.impl.cfg.StandaloneInMemProcessEngineConfiguration
     * @see org.activiti.engine.impl.cfg.multitenant.MultiSchemaMultiTenantProcessEngineConfiguration
     * @see org.activiti.spring.SpringProcessEngineConfiguration
     * @see org.activiti.engine.impl.cfg.JtaProcessEngineConfiguration
     */
    @Autowired
    SpringProcessEngineConfiguration configuration;

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }

    /**
     * @throws Exception
     * @see org.activiti.engine.impl.cfg.IdGenerator
     * @see org.activiti.engine.impl.db.DbIdGenerator default
     * @see org.activiti.engine.impl.persistence.StrongUuidGenerator need com.fasterxml.uuid:java-uuid-generator
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        DbIdGenerator idGenerator=new DbIdGenerator();
//        configuration.buildProcessEngine();
//        idGenerator.setCommandExecutor(configuration.getCommandExecutor());

//        configuration.setIdGenerator(new UUIDIdGenerator());
    }
}
