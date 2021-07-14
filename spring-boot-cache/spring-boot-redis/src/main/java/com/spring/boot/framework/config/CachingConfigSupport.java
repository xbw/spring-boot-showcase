package com.spring.boot.framework.config;


import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.caching.provider
 * Order by :
 *
 * @see org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration.CacheConfigurationImportSelector#selectImports
 * @see org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
 */
@Configuration
public class CachingConfigSupport /*extends CachingConfigurerSupport*/ {

    private static Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder(target.getClass().getName());
        sb = new StringBuilder(target.getClass().getSimpleName());
        sb.append(".").append(method.getName());
        for (Object obj : params) {
            sb.append(".").append(obj);
        }
        return sb.toString();
    }

    /**
     * Inactive when has no extends CachingConfigurerSupport
     *
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @see org.springframework.cache.interceptor.SimpleKeyGenerator
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return CachingConfigSupport::generate;
    }
}
