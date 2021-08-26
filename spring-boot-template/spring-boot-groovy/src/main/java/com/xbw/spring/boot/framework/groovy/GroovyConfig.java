package com.xbw.spring.boot.framework.groovy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.groovy.GroovyMarkupConfigurer;

/**
 * http://docs.groovy-lang.org/docs/next/html/documentation/template-engines.html
 * @author xbw
 * @see org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration
 * @see org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateProperties
 */
public class GroovyConfig {

    @Autowired
    GroovyMarkupConfigurer configurer;

}
