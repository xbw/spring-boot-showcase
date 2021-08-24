package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.project.endpoint.JerseyEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;


/**
 * @see org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration
 * @see org.springframework.boot.autoconfigure.jersey.JerseyProperties
 */
@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(JerseyEndpoint.class);
    }
}