package com.spring.boot.framework.cache.ext.jcs3;

import org.apache.commons.jcs3.engine.control.CompositeCache;
import org.apache.commons.jcs3.engine.control.CompositeCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class JCS3CacheManager extends AbstractTransactionSupportingCacheManager {

    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
    private final Collection<String> customCacheNames = new CopyOnWriteArrayList<>();
    private CompositeCacheManager cacheManager;

    public JCS3CacheManager() {
    }

    public JCS3CacheManager(CompositeCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public CompositeCacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(@Nullable CompositeCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void afterPropertiesSet() {
        if (getCacheManager() == null) {
            setCacheManager(CompositeCacheManager.getInstance());
        }
        super.afterPropertiesSet();
    }


    @Override
    protected Collection<? extends Cache> loadCaches() {
        CompositeCacheManager cacheManager = getCacheManager();
        Set<String> names = cacheManager.getCacheNames();
        Collection<Cache> caches = new LinkedHashSet<Cache>(names.size());
        names.forEach(name -> caches.add(new JCS3Cache(cacheManager.getCache(name))));
        return caches;
    }

    @Override
    protected Cache getMissingCache(String name) {
        // Check the JCSCache cache again (in case the cache was added at runtime)
        CompositeCache jCSCache = getCacheManager().getCache(name);
        if (jCSCache != null) {
            return new JCS3Cache(jCSCache);
        }
        return null;
    }

    public void setCacheNames(@Nullable Collection<String> cacheNames) {
        cacheNames.forEach(cacheName -> getMissingCache(cacheName));
    }
}
