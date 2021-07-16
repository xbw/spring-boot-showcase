package com.xbw.spring.boot.framework.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xbw
 */
@ConfigurationProperties(prefix = "spring.cache.memcached")
public class MemcachedProperties {
    private final Whalin whalin = new Whalin();
    private final Dustin dustin = new Dustin();
    private final Dennis dennis = new Dennis();
    private String address;
    private String host;
    private int port;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Whalin getWhalin() {
        return whalin;
    }

    public Dustin getDustin() {
        return dustin;
    }

    public Dennis getDennis() {
        return dennis;
    }

    /**
     * Memcached-Java-Client specific cache properties.
     */
    public static class Whalin {
        private Integer[] weights;
        // @Value("#{'${spring.cache.memcached.whalin.servers}'.split(',')}")
        private String[] servers;
        private boolean nagle;

        private int initConn;
        private int minConn;
        private int maxConn;
        private int minIdle;
        private int maxIdle;
        private int socketTO;
        private int socketConnectTO;

        public Integer[] getWeights() {
            return weights;
        }

        public void setWeights(Integer[] weights) {
            this.weights = weights;
        }

        public String[] getServers() {
            return servers;
        }

        public void setServers(String[] servers) {
            this.servers = servers;
        }

        public boolean isNagle() {
            return nagle;
        }

        public void setNagle(boolean nagle) {
            this.nagle = nagle;
        }

        public int getInitConn() {
            return initConn;
        }

        public void setInitConn(int initConn) {
            this.initConn = initConn;
        }

        public int getMinConn() {
            return minConn;
        }

        public void setMinConn(int minConn) {
            this.minConn = minConn;
        }

        public int getMaxConn() {
            return maxConn;
        }

        public void setMaxConn(int maxConn) {
            this.maxConn = maxConn;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getSocketTO() {
            return socketTO;
        }

        public void setSocketTO(int socketTO) {
            this.socketTO = socketTO;
        }

        public int getSocketConnectTO() {
            return socketConnectTO;
        }

        public void setSocketConnectTO(int socketConnectTO) {
            this.socketConnectTO = socketConnectTO;
        }
    }

    /**
     * Spymemcached specific cache properties.
     */
    public static class Dustin {
        // TODO
    }

    /**
     * XMemcached specific cache properties.
     */
    public static class Dennis {
        private boolean failureMode;
        private boolean sanitizeKeys;
        private long opTimeout;
        private long connectTimeout;
        private int connectionPoolSize;

        public boolean isFailureMode() {
            return failureMode;
        }

        public void setFailureMode(boolean failureMode) {
            this.failureMode = failureMode;
        }

        public boolean isSanitizeKeys() {
            return sanitizeKeys;
        }

        public void setSanitizeKeys(boolean sanitizeKeys) {
            this.sanitizeKeys = sanitizeKeys;
        }

        public long getOpTimeout() {
            return opTimeout;
        }

        public void setOpTimeout(long opTimeout) {
            this.opTimeout = opTimeout;
        }

        public long getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public int getConnectionPoolSize() {
            return connectionPoolSize;
        }

        public void setConnectionPoolSize(int connectionPoolSize) {
            this.connectionPoolSize = connectionPoolSize;
        }
    }
}
