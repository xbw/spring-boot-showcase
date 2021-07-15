package com.xbw.spring.boot.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(name = "spring.cache.type")
@EnableCaching
@Configuration
@Deprecated
public class CacheConfig implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheManager cacheManager;

    /**
     * @see org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
     * @see org.springframework.boot.autoconfigure.cache.GuavaCacheConfiguration
     * @see org.springframework.cache.guava.GuavaCacheManager
     * @see org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
     * @see com.couchbase.client.spring.cache.CouchbaseCacheManager
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("CacheManager -> {}", cacheManager.getClass());
    }
}
