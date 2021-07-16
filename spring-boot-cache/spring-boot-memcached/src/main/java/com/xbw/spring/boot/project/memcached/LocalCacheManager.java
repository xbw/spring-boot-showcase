package com.xbw.spring.boot.project.memcached;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xbw
 */
@Deprecated
public class LocalCacheManager {

    private static final Logger logger = LoggerFactory.getLogger(LocalCacheManager.class);

    private static Map<String, List<?>> map = new ConcurrentHashMap<String, List<?>>();// 缓存容器
    private volatile static LocalCacheManager localCacheManager;// 缓存实例对象
    private static MemcachedManager memcachedManager;
    private volatile long updateTime;// 更新缓存时记录的时间
    private volatile boolean update = true;// 为false时表示当前没有更新缓存，为true时表示当前正在更新缓存

    private LocalCacheManager() {
        memcachedManager = MemcachedManager.getInstance();
        loadCache();// 加载缓存
        updateTime = System.currentTimeMillis();// 缓存更新时间
    }

    /**
     * 采用单例模式获取缓存对象实例
     *
     * @return
     */
    public static LocalCacheManager getInstance() {
        if (null == localCacheManager) {
            synchronized (LocalCacheManager.class) {
                if (null == localCacheManager) {
                    localCacheManager = new LocalCacheManager();
                }
            }
        }
        return localCacheManager;
    }

    /**
     * 装载缓存
     */
    private void loadCache() {
        update = true;// 正在更新
        /********** 数据处理，将数据放入cacheMap缓存中 **begin ******/
        try {
            if (null != memcachedManager.get("default")) {
                map.put("default", (List<?>) memcachedManager.get("default"));
            }
        } finally {
            update = false;// 更新已完成
        }
        /********** 数据处理，将数据放入cacheMap缓存中 ***end *******/

    }

    /**
     * 返回缓存对象
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List getLocalCache(String key) {
        long currentTime = System.currentTimeMillis();

        if (isUpdate()) {
            logger.info("缓存正在更新");
            return null;
        }

        if (isTimeOut(currentTime)) {// 如果当前缓存正在更新或者缓存超出时限，需重新加载
            synchronized (this) {
                reLoadCache();
                updateTime = currentTime;
            }
        }

        return map.get(key);
    }

    private boolean isTimeOut(long currentTime) {
        return ((currentTime - getUpdateTime()) > 5 * 60 * 1000);// 超过时限，超时缓存正在重新加载
    }

    /**
     * 获取缓存项大小
     *
     * @return
     */
    @SuppressWarnings("unused")
    private int getCacheSize() {
        return map.size();
    }

    /**
     * 获取更新时间
     *
     * @return
     */
    private long getUpdateTime() {
        return updateTime;
    }

    /**
     * 获取更新标志
     *
     * @return
     */
    private boolean isUpdate() {
        return update;
    }

    /**
     * 重新装载
     */
    private void reLoadCache() {
        map.clear();
        loadCache();
    }
}