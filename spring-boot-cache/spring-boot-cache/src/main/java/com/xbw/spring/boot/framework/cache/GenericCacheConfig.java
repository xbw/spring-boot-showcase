package com.xbw.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration
 * @see org.springframework.cache.support.SimpleCacheManager
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "generic")
@Configuration
public class GenericCacheConfig {

}
