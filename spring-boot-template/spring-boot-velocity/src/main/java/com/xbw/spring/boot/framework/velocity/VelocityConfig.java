package com.xbw.spring.boot.framework.velocity;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * http://velocity.apache.org/engine/2.0/upgrading.html
 * http://velocity.apache.org/tools/devel/upgrading.html
 * @author xbw
 * @see org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration
 * @see org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties
 */
@Configuration
@ComponentScan(basePackages = "org.springframework.boot.autoconfigure.velocity")
public class VelocityConfig {

}
