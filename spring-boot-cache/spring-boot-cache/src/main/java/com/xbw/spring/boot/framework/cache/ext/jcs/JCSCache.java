package com.xbw.spring.boot.framework.cache.ext.jcs;

import org.apache.commons.jcs.engine.CacheElement;
import org.apache.commons.jcs.engine.behavior.ICacheElement;
import org.apache.commons.jcs.engine.control.CompositeCache;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.io.IOException;
import java.util.concurrent.Callable;

@SuppressWarnings("unchecked")
public class JCSCache implements Cache {

    private final CompositeCache cache;

    public JCSCache(CompositeCache cache) {
        this.cache = cache;
    }

    @Override
    public final String getName() {
        return this.cache.getCacheName();
    }

    @Override
    public final CompositeCache getNativeCache() {
        return this.cache;
    }

    @Override
    public ValueWrapper get(Object key) {
        ICacheElement element = this.cache.get(key);
        return (element != null ? new SimpleValueWrapper(element.getVal()) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        ICacheElement element = this.cache.get(key);
        Object value = (element != null ? element.getVal() : null);
        if (type != null && !type.isInstance(value)) {
            throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
        }
        return (T) value;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        ICacheElement element = this.cache.get(key);
        Object value = (element != null ? element.getVal() : null);
        if (value == null) {
            try {
                return valueLoader.call();
            } catch (Exception e) {
                throw new ValueRetrievalException(key, valueLoader, e);
            }
        } else {
            return (T) value;
        }
    }

    @Override
    public void put(Object key, Object value) {
        try {
            CacheElement ce = new CacheElement(this.cache.getCacheName(), key, value);
            ce.setElementAttributes(this.cache.getElementAttributes());
            this.cache.update(ce);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper valueWrapper = get(key);
        if (valueWrapper != null) {
            return valueWrapper;
        } else {
            put(key, value);
            return null;
        }
    }

    @Override
    public void evict(Object key) {
        this.cache.remove(key);
    }

    @Override
    public void clear() {
        try {
            this.cache.removeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
