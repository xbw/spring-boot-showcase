package com.xbw.spring.boot.framework.config;


import com.xbw.spring.boot.framework.activiti.UUIDIdGenerator;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.db.DbIdGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 * @see org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration
 * @see org.activiti.spring.boot.ProcessEngineAutoConfiguration
 * @see org.activiti.core.common.spring.identity.config.ActivitiSpringIdentityAutoConfiguration
 * @see org.activiti.spring.boot.ActivitiProperties
 */
@Configuration
public class ActivitiConfig implements InitializingBean {
    @Autowired
    SpringProcessEngineConfiguration configuration;

    @Bean
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }

    /**
     * @throws Exception
     * @see org.activiti.engine.impl.cfg.IdGenerator
     * @see org.activiti.engine.impl.db.DbIdGenerator
     * @see org.activiti.engine.impl.persistence.StrongUuidGenerator default
     */
    @Override
    public void afterPropertiesSet() throws Exception {
//        DbIdGenerator idGenerator=new DbIdGenerator();
//        configuration.buildProcessEngine();
//        idGenerator.setCommandExecutor(configuration.getCommandExecutor());

//        configuration.setIdGenerator(new UUIDIdGenerator());
    }
}
