package com.spring.boot.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache-jsr-107-summary
 */
@CacheConfig(cacheNames = {"spring:boot"})
@Service
public class CacheService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Cacheable //SimpleKey
    public String get() {
        return now("@Cacheable");
    }

    @Cacheable(/*, key = "#id"*/)//default key = "#id",Override by keyGenerator()
    public String get(Long id) {
        return now("@Cacheable");
    }

    @CachePut
    public String put() {
        return now("@CachePut");
    }

    @CachePut
    public String put(Long id) {
        return now("@CachePut");
    }

    @CacheEvict(allEntries = true)
    public String evict() {
        return now("@CacheEvict");
    }

    @CacheEvict
    public String evict(Long id) {
        return now("@CacheEvict");
    }

    private String now(String type) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = type + " -> " + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS"));
        logger.info(now);
        return now;
    }
}
