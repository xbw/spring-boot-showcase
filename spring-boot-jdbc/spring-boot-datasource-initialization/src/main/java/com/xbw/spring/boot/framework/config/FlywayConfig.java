package com.xbw.spring.boot.framework.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration
 */
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "flyway")
@EnableConfigurationProperties(FlywayProperties.class)
@Configuration(proxyBeanMethods = false)
public class FlywayConfig implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    Flyway flyway;
    @Autowired
    FlywayProperties flywayProperties;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Migration-Guide#flyway
     * https://www.baeldung.com/spring-boot-flyway-repair
     * https://stackoverflow.com/questions/65480577/flyway-migration-not-working-repair-required
     * <p>
     * Either revert the changes to the migration, or run repair to update the schema history.
     *
     * @return
     */
    @Primary
    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            flyway.repair();
            flyway.migrate();
        };
    }

    /**
     * https://www.baeldung.com/database-migrations-with-flyway
     * https://github.com/spring-projects/spring-boot/issues/4295
     * <p>
     * We can leave Flyway enabled and implement an empty FlywayMigrationStrategy
     * This will effectively disable Flyway migration on application startup.
     *
     * @return
     */
    @ConditionalOnMissingBean
    @Bean
    public FlywayMigrationStrategy emptyFlywayMigrationStrategy() {
        return flyway -> {
            // do nothing
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("FlywayProperties -> {}", objectMapper.writeValueAsString(flywayProperties));
        logger.info("MigrationInfoService -> {}", objectMapper.writeValueAsString(flyway.info()));
    }

}
