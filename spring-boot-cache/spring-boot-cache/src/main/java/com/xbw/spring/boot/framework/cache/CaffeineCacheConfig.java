package com.xbw.spring.boot.framework.cache;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @see org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
 * @see org.springframework.cache.caffeine.CaffeineCacheManager
 * @see org.springframework.cache.caffeine.CaffeineCache
 * @see com.github.benmanes.caffeine.cache.BoundedLocalCache.BoundedLocalLoadingCache
 * @see com.github.benmanes.caffeine.cache.CaffeineSpec
 */
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "caffeine")
@Configuration
public class CaffeineCacheConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheProperties cacheProperties;

//    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine, CacheLoader<Object, Object> cacheLoader) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        Assert.notNull(caffeine, "Caffeine must not be null");
        cacheManager.setCaffeine(caffeine);

        Assert.notNull(cacheLoader, "CacheLoader must not be null");
        cacheManager.setCacheLoader(cacheLoader);

        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            cacheManager.setCacheNames(cacheNames);
        }

        return cacheManager;
    }

//    @Bean
    public Caffeine<Object, Object> caffeine() {
        Assert.notNull(cacheProperties, "CacheProperties must not be null");
        String cacheSpecification = cacheProperties.getCaffeine().getSpec();
        logger.info("CaffeineConfig -> {}", cacheSpecification);
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder();
        if (cacheSpecification != null) {
            caffeine = Caffeine.from(cacheSpecification);
        }
        return caffeine;
    }

    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception {
                return null;
            }

            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue; // real refresh
            }
        };
        return cacheLoader;
    }

}
