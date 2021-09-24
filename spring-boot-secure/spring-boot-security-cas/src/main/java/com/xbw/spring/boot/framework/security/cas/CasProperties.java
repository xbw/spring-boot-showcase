package com.xbw.spring.boot.framework.security.cas;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.cas")
public class CasProperties {
    private String serverUrlPrefix;

    private String serverLoginUrl;

    private String serverLogoutUrl;

    private String clientUrlPrefix;

    private String clientLoginUrl;

    public String getServerUrlPrefix() {
        return serverUrlPrefix;
    }

    public void setServerUrlPrefix(String serverUrlPrefix) {
        this.serverUrlPrefix = serverUrlPrefix;
    }

    public String getServerLoginUrl() {
        return serverLoginUrl;
    }

    public void setServerLoginUrl(String serverLoginUrl) {
        this.serverLoginUrl = serverLoginUrl;
    }

    public String getServerLogoutUrl() {
        return serverLogoutUrl;
    }

    public void setServerLogoutUrl(String serverLogoutUrl) {
        this.serverLogoutUrl = serverLogoutUrl;
    }

    public String getClientUrlPrefix() {
        return clientUrlPrefix;
    }

    public void setClientUrlPrefix(String clientUrlPrefix) {
        this.clientUrlPrefix = clientUrlPrefix;
    }

    public String getClientLoginUrl() {
        return clientLoginUrl;
    }

    public void setClientLoginUrl(String clientLoginUrl) {
        this.clientLoginUrl = clientLoginUrl;
    }

    public String getLoginUrl() {
        return  serverLoginUrl+ "?service=" + clientLoginUrl;
    }

}