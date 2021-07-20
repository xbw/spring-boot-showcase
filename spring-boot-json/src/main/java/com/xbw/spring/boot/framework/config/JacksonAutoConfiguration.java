package com.xbw.spring.boot.framework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @see org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(JsonMapper.class)
public class JacksonAutoConfiguration {

//    @Bean
    @ConditionalOnMissingBean
    public JsonMapper jsonMapper(Jackson2ObjectMapperBuilder builder) {
//        JsonMapper jsonMapper = builder.createXmlMapper(false).build(); // Cast Exception
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        JsonMapper jsonMapper = new JsonMapper(objectMapper.getFactory()); // TODO spring.jackson.* inactive
        return jsonMapper;
    }
}
