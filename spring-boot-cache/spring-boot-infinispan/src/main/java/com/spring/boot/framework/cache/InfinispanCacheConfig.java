package com.spring.boot.framework.cache;

import com.spring.boot.framework.config.InfinispanConfig;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer;
import org.infinispan.spring.starter.embedded.InfinispanEmbeddedConfigurationProperties;
import org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * https://github.com/infinispan/infinispan-simple-tutorials/tree/main/spring-integration
 * https://infinispan.org/docs/stable/titles/spring_boot/starter.html
 * https://infinispan.org/infinispan-spring-boot/master/spring_boot_starter.html
 * <p>
 * You can customize the cache manager with the following configuration beans:
 * <p>
 * {@link org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurer}
 * {@link org.infinispan.spring.starter.embedded.InfinispanCacheConfigurer}
 * {@link org.infinispan.configuration.cache.Configuration}
 * {@link org.infinispan.spring.starter.embedded.InfinispanConfigurationCustomizer}
 * {@link org.infinispan.spring.starter.embedded.InfinispanGlobalConfigurationCustomizer}
 * <p>
 * The support of Infinispan in Spring Boot is restricted to the embedded mode and is quite basic.
 * If you want more options, you should use the official Infinispan Spring Boot starter instead.
 *
 * @see org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration.CacheConfigurationImportSelector#selectImports
 * @see org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration
 * @see org.infinispan.spring.starter.embedded.InfinispanEmbeddedAutoConfiguration#defaultCacheManager
 * @see org.infinispan.spring.embedded.provider.SpringEmbeddedCacheManager
 * @see org.infinispan.cache.impl.EncoderCache
 */
//@ConditionalOnProperty(name = "spring.cache.type", havingValue = "infinispan")
@ConditionalOnProperty(name = "infinispan.embedded.enabled", havingValue = "true")
@Configuration
public class InfinispanCacheConfig {
    @Autowired
    InfinispanEmbeddedConfigurationProperties embeddedConfigurationProperties;

    @ConditionalOnBean(InfinispanCacheConfigurer.class)
    @Bean
    public InfinispanGlobalConfigurer globalConfigurer() {
        return () -> GlobalConfigurationBuilder.defaultClusteredBuilder().build();
    }

    /**
     * You can create one InfinispanGlobalConfigurer bean only.
     * However you can create multiple configurations with the other beans.
     */
    @ConditionalOnMissingBean(InfinispanConfig.class)
    @Bean
    public InfinispanCacheConfigurer cacheConfigurer() {
        return manager -> {
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            configurationBuilder.clustering().cacheMode(CacheMode.LOCAL);
            manager.defineConfiguration("spring:boot", configurationBuilder.build());
            manager.defineConfiguration("springboot", configurationBuilder.build());
        };
    }

}
