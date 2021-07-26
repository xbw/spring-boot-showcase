package com.xbw.spring.boot.framework.config;

import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * https://github.com/jOOQ/jOOQ/tree/main/jOOQ-examples
 * @author xbw
 * @see org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration
 */
@Configuration(proxyBeanMethods = false)
public class JooqConfig {
    @Autowired
    DefaultDSLContext dslContext;

}