package com.xbw.spring.boot.framework.cache.ext;

import com.xbw.spring.boot.framework.cache.ext.jcs.JCSCacheManager;
import org.apache.commons.jcs.engine.control.CompositeCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;


@EnableConfigurationProperties(ExtCacheProperties.class)
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "jcs")
@Configuration
public class JCSCacheConfig {

    @Bean
    public CacheManager cacheManager(ExtCacheProperties cacheProperties) {
        JCSCacheManager cacheManager = new JCSCacheManager(getCompositeCacheManager(cacheProperties));
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
