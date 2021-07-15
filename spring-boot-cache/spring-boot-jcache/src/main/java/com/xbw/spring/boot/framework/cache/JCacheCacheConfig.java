package com.xbw.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
 * @see org.springframework.cache.jcache.JCacheCacheManager
 * @see org.springframework.cache.jcache.JCacheCache
 * @see javax.cache.spi.CachingProvider
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "jcache")
@Configuration
public class JCacheCacheConfig {

}
