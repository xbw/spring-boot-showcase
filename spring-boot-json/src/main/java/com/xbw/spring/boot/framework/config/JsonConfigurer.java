package com.xbw.spring.boot.framework.config;


import com.xbw.spring.boot.framework.json.JsonbHttpMessageConverter;
import jakarta.json.bind.Jsonb;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
 * @see org.springframework.boot.autoconfigure.http.JacksonHttpMessageConvertersConfiguration
 * @see org.springframework.boot.autoconfigure.http.GsonHttpMessageConvertersConfiguration
 * @see org.springframework.boot.autoconfigure.http.JsonbHttpMessageConvertersConfiguration
 */
@Configuration
public class JsonConfigurer {


    @ConditionalOnProperty(name = "spring.mvc.converters.preferred-json-mapper", havingValue = "jsonb")
    @Bean
    JsonbHttpMessageConverter jsonbHttpMessageConverter(Jsonb jsonb) {
        JsonbHttpMessageConverter converter = new JsonbHttpMessageConverter();
        converter.setJsonb(jsonb);
        return converter;
    }
}
