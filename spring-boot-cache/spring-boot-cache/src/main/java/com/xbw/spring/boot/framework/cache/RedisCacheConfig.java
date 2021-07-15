package com.xbw.spring.boot.framework.cache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration
 * @see org.springframework.data.redis.cache.RedisCacheManager
 * @see org.springframework.data.redis.cache.RedisCacheConfiguration
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis")
@Configuration
public class RedisCacheConfig {

}
