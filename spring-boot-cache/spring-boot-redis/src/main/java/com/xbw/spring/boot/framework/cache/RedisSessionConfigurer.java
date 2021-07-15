package com.xbw.spring.boot.framework.cache;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * when spring-session-data-redis exist, @EnableRedisHttpSession is default enabled
 * if there is no extension configurator ,this class can be deleted
 */
@Configuration
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
