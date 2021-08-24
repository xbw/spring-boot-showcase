package com.xbw.spring.boot.framework.thymeleaf;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration
 * @see org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties
 */
@Configuration
public class ThymeleafConfig implements InitializingBean {

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void afterPropertiesSet() throws Exception {
        templateEngine.clearTemplateCache();
    }
}