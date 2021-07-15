package com.xbw.spring.boot.framework.cache;

import org.infinispan.spring.starter.remote.InfinispanRemoteConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * https://github.com/infinispan/infinispan-simple-tutorials/tree/main/spring-integration
 * https://infinispan.org/docs/stable/titles/spring_boot/starter.html
 * https://infinispan.org/infinispan-spring-boot/master/spring_boot_starter.html
 * <p>
 * You can customize the cache manager with the following configuration beans:
 * <p>
 * {@link org.infinispan.spring.starter.remote.InfinispanRemoteConfigurer}
 * {@link org.infinispan.configuration.cache.Configuration}
 * {@link org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer}
 *
 * @see org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
 * @see org.infinispan.spring.starter.remote.InfinispanRemoteAutoConfiguration#remoteCacheManager
 * @see org.infinispan.spring.remote.provider.SpringRemoteCacheManager
 * @see org.infinispan.client.hotrod.impl.InvalidatedNearRemoteCache
 */
//@ConditionalOnProperty(name = "spring.cache.type", havingValue = "infinispan")
@ConditionalOnProperty(name = "infinispan.remote.enabled", havingValue = "true")
@Configuration
public class InfinispanCacheRemoteConfig {
    @Autowired
    InfinispanRemoteConfigurationProperties remoteConfigurationProperties;

}
