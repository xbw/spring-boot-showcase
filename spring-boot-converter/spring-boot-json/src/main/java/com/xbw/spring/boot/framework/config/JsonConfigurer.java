package com.xbw.spring.boot.framework.config;


import com.xbw.spring.boot.framework.json.converter.JsonbHttpMessageConverter;
import jakarta.json.bind.Jsonb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @see org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
 * @see org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration
 * @see org.springframework.boot.autoconfigure.http.GsonHttpMessageConvertersConfiguration
 * @see org.springframework.boot.autoconfigure.http.JsonbHttpMessageConvertersConfiguration
 */
@Configuration
public class JsonConfigurer implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ConditionalOnProperty(name = "spring.mvc.converters.preferred-json-mapper", havingValue = "jsonb")
    @Bean
    JsonbHttpMessageConverter jsonbHttpMessageConverter(Jsonb jsonb) {
        JsonbHttpMessageConverter converter = new JsonbHttpMessageConverter();
        converter.setJsonb(jsonb);
        return converter;
    }

    /**
     * Optional
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer
                .defaultContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * @param converters
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#getMessageConverters()
     * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addDefaultHttpMessageConverters(List)
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(converter -> {
            logger.info("HttpMessageConverter -> {}", converter.getClass().getName());
        });
    }

}
