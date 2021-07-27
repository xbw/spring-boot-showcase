package com.xbw.spring.boot.framework.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html
 */
@Configuration
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {

    /**
     * https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#format-FormatterRegistrar-SPI
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
        registrar.setUseIsoFormat(true);
        registrar.registerFormatters(registry);
    }
}