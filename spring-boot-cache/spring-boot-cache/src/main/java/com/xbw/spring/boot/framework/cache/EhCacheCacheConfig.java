package com.xbw.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration
 * @see org.springframework.cache.ehcache.EhCacheCacheManager
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "ehcache")
@Configuration
public class EhCacheCacheConfig {

}
