package com.xbw.spring.boot.framework.freemarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration
 * @see org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties
 */
public class FreemarkerConfig {

    @Autowired
    FreeMarkerConfigurer configurer;
    @Autowired
    freemarker.template.Configuration configuration;

}
