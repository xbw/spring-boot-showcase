package com.xbw.spring.boot.framework.config;


import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.ProcessEngine;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.flowable.spring.boot.AbstractEngineAutoConfiguration
 * @see org.flowable.spring.boot.AbstractSpringEngineAutoConfiguration
 * @see org.flowable.spring.boot.ProcessEngineAutoConfiguration
 * @see org.flowable.spring.boot.FlowableProperties
 * @see org.flowable.spring.boot.ProcessEngineServicesAutoConfiguration
 * @see org.flowable.spring.boot.RestApiAutoConfiguration
 */
@Configuration
public class FlowableConfig implements InitializingBean {

    @Autowired
    SpringProcessEngineConfiguration configuration;

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }

    /**
     * @throws Exception
     * @see org.flowable.common.engine.impl.cfg.IdGenerator
     * @see org.flowable.engine.impl.db.DbIdGenerator
     * @see org.flowable.common.engine.impl.persistence.StrongUuidGenerator default
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        DbIdGenerator idGenerator=new DbIdGenerator();
//        configuration.buildProcessEngine();
//        idGenerator.setCommandExecutor(configuration.getCommandExecutor());

//        configuration.setIdGenerator(new UUIDIdGenerator());
    }
}
