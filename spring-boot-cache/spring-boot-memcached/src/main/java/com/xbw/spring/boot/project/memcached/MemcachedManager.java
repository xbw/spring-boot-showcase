package com.xbw.spring.boot.project.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * @author xbw
 */
@Deprecated
@SuppressWarnings("unchecked")
public class MemcachedManager {

    private static MemcachedManager instance;

    static {
        // 创建一个Socked连接池实例
        SockIOPool pool = SockIOPool.getInstance();
        // 向连接池设置服务器和权重
        pool.setServers(new String[]{"127.0.0.1:11211"});
        pool.setWeights(new Integer[]{1});
        pool.setNagle(false);
        // 设置初始连接5
        pool.setInitConn(5);
        // 设置最小连接5
        pool.setMinConn(5);
        // 设置最大连接250
        pool.setMaxConn(250);
        // 设置每个连接最大空闲时间3个小时
        pool.setMaxIdle(1000 * 60 * 60 * 3);
        pool.setSocketTO(3000);
        pool.setSocketConnectTO(0);
        pool.initialize();
    }

    private MemCachedClient cache;

    private MemcachedManager() {
        this.cache = new MemCachedClient();
    }

    /**
     * 获取缓存管理器唯一实例
     *
     * @return
     */
    public static MemcachedManager getInstance() {
        synchronized (MemcachedManager.class) {
            if (instance == null) {
                instance = new MemcachedManager();
            }
        }
        return instance;
    }

    /**
     * 增加缓存
     *
     * @param key   缓存标识
     * @param value 缓存数据
     * @return 是否成功
     */
    public <T> boolean add(String key, T value) {
        return cache.add(key, value);
    }

    /**
     * 更新缓存
     *
     * @param key   缓存标识
     * @param value 缓存数据
     * @return 是否成功
     */
    public <T> boolean update(String key, T value) {
        return cache.replace(key, value);
    }

    /**
     * 更新缓存,如果有则替换
     *
     * @param key   缓存标识
     * @param value 缓存数据
     * @return 是否成功
     */
    public <T> boolean merge(String key, T value) {
        return cache.set(key, value);
    }

    /**
     * 移除缓存
     *
     * @param key 缓存标识
     * @return 是否成功
     */
    public <T> boolean remove(String key) {
        return cache.delete(key);
    }

    /**
     * 根据标识获取缓存数据
     *
     * @param key 缓存标识
     * @return 缓存数据<T>
     */
    public <T> T get(String key) {
        return (T) cache.get(key);
    }

    public MemCachedClient getCache() {
        return cache;
    }
}

