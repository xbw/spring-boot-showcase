package com.xbw.spring.boot.framework.config;


import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.caching.provider
 *
 * @see org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration.CacheConfigurationImportSelector#selectImports
 * @see org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
 */
@Configuration
public class CachingConfigSupport /*extends CachingConfigurerSupport*/ {

    /**
     * Inactive when has no extends CachingConfigurerSupport
     *
     * @return org.springframework.cache.interceptor.KeyGenerator
     * @see org.springframework.cache.interceptor.SimpleKeyGenerator
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder(target.getClass().getName());
            sb = new StringBuilder(target.getClass().getSimpleName());
            sb.append(".").append(method.getName());
            for (Object obj : params) {
                sb.append(".").append(obj);
            }
            return sb.toString();
        };
    }

}
