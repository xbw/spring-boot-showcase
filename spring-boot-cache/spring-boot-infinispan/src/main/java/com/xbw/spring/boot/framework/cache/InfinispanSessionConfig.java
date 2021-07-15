package com.xbw.spring.boot.framework.cache;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.spring.embedded.session.configuration.EnableInfinispanEmbeddedHttpSession;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://infinispan.org/infinispan-spring-boot/master/spring_boot_starter.html#sb_starter_session
 */
@ConditionalOnProperty(name = "infinispan.embedded.enabled", havingValue = "true")
@EnableInfinispanEmbeddedHttpSession
@Configuration
public class InfinispanSessionConfig {
    @Bean
    public InfinispanCacheConfigurer infinispanSession() {
        return manager -> {
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.clustering().cacheMode(CacheMode.LOCAL);
            manager.defineConfiguration("sessions", configurationBuilder.build());
        };
    }
}
