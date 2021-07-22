package com.xbw.spring.boot.framework.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.xbw.spring.boot.framework.json.gson.LocalDateTimeSerializers;
import com.xbw.spring.boot.framework.json.gson.LocalDateTimeTypeAdapter;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({JacksonProperties.class, GsonProperties.class})
public class LocalDateTimeConfig {
    private String pattern = "yyyy-MM-dd HH:mm:ss";

    //    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer(JacksonProperties properties) {
        return new LocalDateTimeSerializer(getFormatter(properties.getDateFormat()));
    }

    /**
     * Jackson
     *
     * @param localDateTimeDeserializer
     * @return
     */
//    @Bean
    public Jackson2ObjectMapperBuilderCustomizer localDateTimeJackson2ObjectMapperBuilderCustomizer(LocalDateTimeSerializer localDateTimeDeserializer) {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer);
    }

    /**
     * Jackson
     *
     * @param properties
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer localDateTimeJackson2ObjectMapperBuilderCustomizer(JacksonProperties properties) {
        return builder -> builder.serializerByType(LocalDateTime.class,
                new LocalDateTimeSerializer(getFormatter(properties.getDateFormat())));
    }

    /**
     * Gson
     *
     * @param properties
     * @return
     */
    @Bean
    public GsonBuilderCustomizer localDateTimeGsonBuilderCustomizer(GsonProperties properties) {
        return builder -> {
            builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()); // Priority use
            builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializers());
        };
    }

    private DateTimeFormatter getFormatter(String pattern) {
        if (StringUtils.hasLength(pattern)) {
            this.pattern = pattern;
        }
        return DateTimeFormatter.ofPattern(this.pattern);
    }

}