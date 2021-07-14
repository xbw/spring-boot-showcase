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

import java.util.Collection;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class CacheTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CacheManager cacheManager;
    @Autowired
    CacheService caffeineService;

    @Test
    void cache() {
        logger.info("CacheManager -> {}", cacheManager.getClass());
        cacheManager.getCacheNames()
                .forEach(cacheName -> logger.info("CacheName -> {} , Cache -> {} , NativeCache -> {}",
                        cacheName, cacheManager.getCache(cacheName),
                        cacheManager.getCache(cacheName).getNativeCache()));
    }

    @Test
    void get() {
        for (int i = 0; i < 2; i++) {
            logger.info("i = {}, get() -> {}", i, caffeineService.get());
        }
    }

    @Test
    void getId() {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 2; i++) {
                logger.info("j = {}, i = {}, get() -> {}", j, i, caffeineService.get(Long.valueOf(i)));
            }
        }
    }

    @Test
    void put() {
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                logger.info("i = {}, put() -> {}", i, caffeineService.put());
            }
            logger.info("i = {}, get() -> {}", i, caffeineService.get());
        }
    }

    @Test
    void putId() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 2; i++) {
                if (j == 2) {
                    logger.info("j = {}, i = {}, put() -> {}", j, i, caffeineService.put(Long.valueOf(i)));
                }
                logger.info("j = {}, i = {}, get() -> {}", j, i, caffeineService.get(Long.valueOf(i)));
            }
        }
    }

    @Test
    void evict() {
        for (int i = 0; i < 4; i++) {
            if (i == 2) {
                logger.info("i = {}, evict() -> {}", i, caffeineService.evict());
            }
            logger.info("i = {}, get() -> {}", i, caffeineService.get());
        }
    }

    @Test
    void evictId() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 2; i++) {
                if (j == 2) {
                    logger.info("j = {}, i = {}, evict() -> {}", j, i, caffeineService.evict(Long.valueOf(i)));
                }
                logger.info("j = {}, i = {}, get() -> {}", j, i, caffeineService.get(Long.valueOf(i)));
            }
        }
    }
}
