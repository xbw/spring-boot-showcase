package com.xbw.spring.boot.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.integration.spring.SpringLiquibase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * https://www.baeldung.com/liquibase-refactor-schema-of-java-app
 * https://github.com/liquibase/liquibase-hibernate/wiki
 * <p>
 * https://docs.liquibase.com/tools-integrations/liquibase-hub/setup.html
 * @author xbw
 * @see org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration
 * @see org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
 * @see liquibase.hub.HubUpdater#register(String)
 */
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "liquibase")
@EnableConfigurationProperties(LiquibaseProperties.class)
@AutoConfigureAfter(LiquibaseAutoConfiguration.class)
@Configuration(proxyBeanMethods = false)
public class LiquibaseConfig implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    LiquibaseProperties liquibaseProperties;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    SpringLiquibase liquibase;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("LiquibaseProperties -> {}", objectMapper.writeValueAsString(liquibaseProperties));
    }
}