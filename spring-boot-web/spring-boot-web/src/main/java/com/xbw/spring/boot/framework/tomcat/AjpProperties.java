package com.xbw.spring.boot.framework.tomcat;

import org.apache.coyote.ajp.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xbw
 */
@ConfigurationProperties(prefix = "tomcat.ajp")
public class AjpProperties {
    private boolean enabled = false;
    private boolean flush = true;
    private int packetSize = Constants.MAX_PACKET_SIZE;
    private int port = 8009;
    private boolean secretRequired = false;
    private String secret = "ajp";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFlush() {
        return flush;
    }

    public void setFlush(boolean flush) {
        this.flush = flush;
    }

    public int getPacketSize() {
        return packetSize;
    }

    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSecretRequired() {
        return secretRequired;
    }

    public void setSecretRequired(boolean secretRequired) {
        this.secretRequired = secretRequired;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
