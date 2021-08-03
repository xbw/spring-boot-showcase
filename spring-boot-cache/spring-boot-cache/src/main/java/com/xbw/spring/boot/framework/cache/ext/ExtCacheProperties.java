package com.xbw.spring.boot.framework.cache.ext;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @see org.springframework.boot.autoconfigure.cache.CacheProperties
 */
@ConfigurationProperties(prefix = "spring.cache")
public class ExtCacheProperties {

    private final JCS jcs = new JCS();
    /**
     * Cache type. By default, auto-detected according to the environment.
     */
    private ExtCacheType type;
    /**
     * Comma-separated list of cache names to create if supported by the underlying cache
     * manager. Usually, this disables the ability to create additional caches on-the-fly.
     */
    private List<String> cacheNames = new ArrayList<>();

    public ExtCacheType getType() {
        return this.type;
    }

    public void setType(ExtCacheType type) {
        this.type = type;
    }

    public List<String> getCacheNames() {
        return this.cacheNames;
    }

    public void setCacheNames(List<String> cacheNames) {
        this.cacheNames = cacheNames;
    }

    public JCS getJCS() {
        return this.jcs;
    }

    /**
     * Resolve the config location if set.
     *
     * @param config the config resource
     * @return the location or {@code null} if it is not set
     * @throws IllegalArgumentException if the config attribute is set to an unknown
     *                                  location
     */
    public Resource resolveConfigLocation(Resource config) {
        if (config != null) {
            Assert.isTrue(config.exists(),
                    () -> "Cache configuration does not exist '" + config.getDescription() + "'");
            return config;
        }
        return null;
    }

    /**
     * JCS specific cache properties.
     */
    public static class JCS {

        /**
         * The location of the configuration file to use to initialize JCS.
         */
        private Resource config;

        public Resource getConfig() {
            return this.config;
        }

        public void setConfig(Resource config) {
            this.config = config;
        }

    }

}
