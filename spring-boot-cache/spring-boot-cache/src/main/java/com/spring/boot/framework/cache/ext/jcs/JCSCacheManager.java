package com.spring.boot.framework.cache.ext.jcs;

import org.apache.commons.jcs.engine.control.CompositeCache;
import org.apache.commons.jcs.engine.control.CompositeCacheManager;
import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class JCSCacheManager extends AbstractTransactionSupportingCacheManager {

    private final Map<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
    private final Collection<String> customCacheNames = new CopyOnWriteArrayList<>();
    private CompositeCacheManager cacheManager;

    public JCSCacheManager() {
    }

    public JCSCacheManager(CompositeCacheManager cacheManager) {
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
        String[] names = cacheManager.getCacheNames();
        Collection<Cache> caches = new LinkedHashSet<>(names.length);
        for (String name : names) {
            caches.add(new JCSCache(cacheManager.getCache(name)));
        }
        return caches;
    }

    @Override
    protected Cache getMissingCache(String name) {
        // Check the JCSCache cache again (in case the cache was added at runtime)
        CompositeCache jCSCache = getCacheManager().getCache(name);
        if (jCSCache != null) {
            return new JCSCache(jCSCache);
        }
        return null;
    }

    public void setCacheNames(@Nullable Collection<String> cacheNames) {
        cacheNames.forEach(cacheName -> getMissingCache(cacheName));
    }
}
