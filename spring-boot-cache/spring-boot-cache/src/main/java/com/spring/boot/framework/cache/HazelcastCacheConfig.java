package com.spring.boot.framework.cache;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration
 * @see org.springframework.boot.autoconfigure.cache.HazelcastInstanceConfiguration
 * @see com.hazelcast.spring.cache.HazelcastCacheManager
 */
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "hazelcast")
@Configuration
public class HazelcastCacheConfig {

}
