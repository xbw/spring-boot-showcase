package com.xbw.spring.boot.framework.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactory;

/**
 * https://www.baeldung.com/spring-template-engines
 * @author xbw
 * @see org.springframework.boot.autoconfigure.template.AbstractTemplateViewResolverProperties
 */
@Configuration
public class TemplateConfig implements InitializingBean {
    @Autowired
    VelocityEngineFactory engineFactory;
    @Autowired
    VelocityEngine engine;

    @Override
    public void afterPropertiesSet() throws Exception {
        String vm = "org/springframework/web/servlet/view/velocity/spring.vm";
        System.out.println("VelocityEngineFactory getTemplate: " + engineFactory.createVelocityEngine().getTemplate(vm).getName());
        System.out.println("VelocityEngine getTemplate: " + engine.getTemplate(vm).getName());
    }
}
