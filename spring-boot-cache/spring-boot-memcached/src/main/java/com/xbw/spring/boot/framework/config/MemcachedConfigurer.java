package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.framework.cache.MemcachedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * https://github.com/sixhours-team/memcached-spring-boot
 * https://github.com/bmatthews68/memcached-spring-boot-starter
 *
 * @author xbw
 */
@EnableConfigurationProperties(MemcachedProperties.class)
@Configuration
public class MemcachedConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * https://stackoverflow.com/questions/44507525/how-to-configure-spring-boot-with-memcached
     *
     * @param properties
     * @return
     */
    @Bean
    public com.whalin.MemCached.MemCachedClient memcached(MemcachedProperties properties) {

        com.whalin.MemCached.SockIOPool sockIOPool = com.whalin.MemCached.SockIOPool.getInstance();
        MemcachedProperties.Whalin whalin = properties.getWhalin();
        sockIOPool.setServers(whalin.getServers());
        sockIOPool.setWeights(whalin.getWeights());
        sockIOPool.setNagle(whalin.isNagle());

        sockIOPool.setInitConn(whalin.getInitConn());
        sockIOPool.setMinConn(whalin.getMinConn());
        sockIOPool.setMaxConn(whalin.getMaxConn());
        sockIOPool.setMaxIdle(whalin.getMaxIdle());
        sockIOPool.setSocketTO(whalin.getSocketTO());
        sockIOPool.setSocketConnectTO(whalin.getSocketConnectTO());
        sockIOPool.initialize();

        return new com.whalin.MemCached.MemCachedClient();
    }

    @Bean
    public net.spy.memcached.MemcachedClient spymemcached(MemcachedProperties properties) {
        try {
//            net.spy.memcached.MemcachedClient spymemcached = new net.spy.memcached.MemcachedClient(new InetSocketAddress(properties.getHost(), properties.getPort()));
            net.spy.memcached.MemcachedClient spymemcached = new net.spy.memcached.MemcachedClient(
                    net.spy.memcached.AddrUtil.getAddresses(properties.getAddress()));
            return spymemcached;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Bean
    public net.rubyeye.xmemcached.MemcachedClient xMemcached(MemcachedProperties properties) {
        net.rubyeye.xmemcached.MemcachedClientBuilder builder = new net.rubyeye.xmemcached.XMemcachedClientBuilder(
                net.rubyeye.xmemcached.utils.AddrUtil.getAddresses(properties.getAddress()));
        builder.setConnectTimeout(properties.getDennis().getConnectTimeout());
        try {
            net.rubyeye.xmemcached.MemcachedClient xMemcached = builder.build();
            return xMemcached;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
