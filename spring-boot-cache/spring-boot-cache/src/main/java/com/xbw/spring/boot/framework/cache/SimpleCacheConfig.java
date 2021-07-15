package com.xbw.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration
 * @see org.springframework.cache.concurrent.ConcurrentMapCacheManager
 * @see org.springframework.cache.concurrent.ConcurrentMapCache
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "simple")
@Configuration
public class SimpleCacheConfig {

}
