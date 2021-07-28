package com.xbw.spring.boot.framework.config;

import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * https://www.simplejavamail.org/configuration.html#section-spring-support
 */
@Configuration
@Import(SimpleJavaMailSpringSupport.class)
public class SimpleJavaMailConfig {

}