package com.xbw.spring.boot.framework.config;


import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * https://www.baeldung.com/jaxb
 * https://github.com/spring-projects/spring-boot/issues/407
 * {@link org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter#canRead(Class, org.springframework.http.MediaType) }
 * need {@code clazz.isAnnotationPresent(XmlRootElement.class) || clazz.isAnnotationPresent(XmlType.class)}
 *
 * @see org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport#addDefaultHttpMessageConverters(List)
 */
@Configuration
public class XmlConfigurer implements WebMvcConfigurer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer localDateTimeJackson2ObjectMapperBuilderCustomizer(JacksonProperties properties) {
        DateTimeFormatter formatter;
        if (StringUtils.hasLength(properties.getDateFormat())) {
            formatter = DateTimeFormatter.ofPattern(properties.getDateFormat());
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        return builder -> builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
    }

//    /**
//     * use {@link #configureMessageConverters(List)}
//     *
//     * @param configurer
//     */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer
//                .defaultContentType(MediaType.APPLICATION_XML);
//    }
//
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
//        while (iterator.hasNext()) {
//            HttpMessageConverter converter = iterator.next();
//            if (converter instanceof MappingJackson2XmlHttpMessageConverter
//                    | converter instanceof Jaxb2RootElementHttpMessageConverter) {
//                iterator.remove();
//                converters.add(0, converter);
//                break;
//            }
//        }
//    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.forEach(converter -> {
            logger.info("HttpMessageConverter -> {}", converter.getClass().getName());
        });
    }

}
