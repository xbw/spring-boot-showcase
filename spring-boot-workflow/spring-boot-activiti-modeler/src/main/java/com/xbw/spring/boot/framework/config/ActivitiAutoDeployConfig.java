package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.framework.activiti.ActivitiProperties;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @author xbw
 */
@EnableConfigurationProperties(ActivitiProperties.class)
@ConfigurationProperties("spring.activiti.deployment-mode")
@Configuration
public class ActivitiAutoDeployConfig implements InitializingBean {
    @Autowired
    ActivitiProperties activitiProperties;
    @Autowired
    SpringProcessEngineConfiguration configuration;

    /**
     * AutoDeploy
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        configuration.setDeploymentMode(activitiProperties.getDeploymentMode()); // default,single-resource,resource-parent-folder
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] processDefinitions = resourcePatternResolver.getResources("classpath:processes/**/*.*");
            configuration.setDeploymentResources(processDefinitions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
