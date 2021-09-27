package com.xbw.spring.boot.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xbw
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${file.local.path}")
    private String localPath;
    @Value("${file.virtual.path}")
    private String virtualPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(virtualPath).addResourceLocations("file:" + localPath);
    }
}
