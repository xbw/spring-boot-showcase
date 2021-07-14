package com.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration
 * @see org.springframework.cache.jcache.JCacheCacheManager
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "jcache")
@Configuration
public class JCacheCacheConfig {

}
