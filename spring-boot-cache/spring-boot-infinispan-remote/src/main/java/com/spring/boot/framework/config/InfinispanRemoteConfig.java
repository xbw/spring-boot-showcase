package com.spring.boot.framework.config;


import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(name = "infinispan.remote.enabled", havingValue = "true")
@Configuration
public class InfinispanRemoteConfig implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RemoteCacheManager cacheManager;
    @Autowired
    CacheProperties cacheProperties;

    /**
     * Depends on spring.cache.cache-names
     *
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
        cacheProperties.getCacheNames().forEach(cacheName -> {
            cacheManager.administration()
                    .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
                    .getOrCreateCache(cacheName, new ConfigurationBuilder().build());
            logger.info("cacheName -> {}", cacheName);
        });
    }
}
