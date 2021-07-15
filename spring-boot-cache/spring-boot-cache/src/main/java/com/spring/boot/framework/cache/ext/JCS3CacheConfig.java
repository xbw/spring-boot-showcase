package com.spring.boot.framework.cache.ext;

import com.spring.boot.framework.cache.ext.jcs3.JCS3CacheManager;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.ServletContextResource;

import java.util.List;


@EnableConfigurationProperties(ExtCacheProperties.class)
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "jcs3")
@Configuration
public class JCS3CacheConfig {

    @Bean
    public CacheManager cacheManager(ExtCacheProperties cacheProperties) {
        JCS3CacheManager cacheManager = new JCS3CacheManager(getCompositeCacheManager(cacheProperties));
        List<String> cacheNames = cacheProperties.getCacheNames();
        if (!CollectionUtils.isEmpty(cacheNames)) {
            cacheManager.setCacheNames(cacheNames);
        }
        return cacheManager;
    }

    private CompositeCacheManager getCompositeCacheManager(ExtCacheProperties cacheProperties) {
        ClassPathResource location = (ClassPathResource) cacheProperties.resolveConfigLocation(cacheProperties.getJCS().getConfig());
        if (location != null) {
            return CompositeCacheManager.getInstance("/"+location.getPath());
        } else {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource("cache.ccf");
            Assert.isTrue(resource.exists(), "cache.ccf must exist in classpath");
            return CompositeCacheManager.getInstance();
        }
    }
}
