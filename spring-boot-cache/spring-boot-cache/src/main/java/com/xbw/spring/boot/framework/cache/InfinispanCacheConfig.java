package com.xbw.spring.boot.framework.cache;

import org.infinispan.commons.api.CacheContainerAdmin;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
 * @see org.infinispan.spring.starter.embedded.InfinispanEmbeddedAutoConfiguration#defaultCacheManager
 * @see org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager
 * @see org.infinispan.cache.impl.EncoderCache
 */
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "infinispan")
@Configuration
public class InfinispanCacheConfig implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EmbeddedCacheManager cacheManager;
    @Autowired
    CacheProperties cacheProperties;

    @Override
    public void afterPropertiesSet() throws Exception {
        cacheProperties.getCacheNames().forEach(cacheName -> {
            cacheManager.administration()
                    .withFlags(CacheContainerAdmin.AdminFlag.VOLATILE)
                    .getOrCreateCache(cacheName, new ConfigurationBuilder().build());
            logger.info("cacheName -> {}", cacheName);
        });
    }
}
