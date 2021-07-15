package com.spring.boot.cache;

import com.spring.boot.project.service.CacheService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;

@SpringBootTest
class JCacheTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheManager cacheManager;

    @Test
    void cache() {
        logger.info("CacheManager -> {}", cacheManager.getClass());
        cacheManager.getCacheNames()
                .forEach(cacheName -> logger.info("CacheName -> {}, \nCache -> {}, \nNativeCache -> {}",
                        cacheName, cacheManager.getCache(cacheName).getClass(),
                        cacheManager.getCache(cacheName).getNativeCache().getClass()));
    }

}

