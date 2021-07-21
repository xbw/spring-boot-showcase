package com.xbw.spring.boot.framework.json;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration
 * @see jakarta.json.spi.JsonProvider
 * @see jakarta.json.bind.spi.JsonbProvider
 * @see org.eclipse.yasson.JsonBindingProvider
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Jsonb.class)
@ConditionalOnResource(resources = {"classpath:META-INF/services/jakarta.json.bind.spi.JsonbProvider",
        "classpath:META-INF/services/jakarta.json.spi.JsonProvider"})
public class JsonbAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Jsonb jsonb() {
        return JsonbBuilder.create();
    }

}