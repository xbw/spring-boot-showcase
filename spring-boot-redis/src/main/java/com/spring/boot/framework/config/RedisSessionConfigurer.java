package com.spring.boot.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 实测 Spring Boot 2.x
 * 增加spring-session-data-redis后 @EnableRedisHttpSession 已生效
 * 如果仅使用默认配置，本Class可删除
 */
@Configuration
@ConditionalOnProperty(name = "redis.session.enable", havingValue = "true")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60 * 60 * 24)
public class RedisSessionConfigurer {
    /**
     * https://stackoverflow.com/questions/43908672/how-to-store-values-in-json-with-spring-sessions-and-redis
     *
     * @return
     */
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Object.class);
    }
}
