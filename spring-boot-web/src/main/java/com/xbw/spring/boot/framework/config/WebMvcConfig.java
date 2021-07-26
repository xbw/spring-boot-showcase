package com.xbw.spring.boot.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Spring 5 or Spring Boot 2.x +
 * @author xbw
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(converter -> {
            System.out.printf("converter -> %s%n", converter);
        });
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // When allowCredentials is true, allowedOrigins cannot contain the special value "*" since that cannot be set on the "Access-Control-Allow-Origin" response header.
        // To allow credentials to a set of origins, list them explicitly or consider using "allowedOriginPatterns" instead.
        registry.addMapping("/**")
//                .allowedOrigins("*")
                .allowedOriginPatterns("*") // Since Spring Boot 2.4.0
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600L);
    }

    /**
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }
}
