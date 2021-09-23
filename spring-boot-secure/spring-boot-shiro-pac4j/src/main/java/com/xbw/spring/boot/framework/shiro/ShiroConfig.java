package com.xbw.spring.boot.framework.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 */
@Configuration
public class ShiroConfig {
    /**
     * Thymeleaf Shiro Support
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
