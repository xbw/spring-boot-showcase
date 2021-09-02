package com.xbw.spring.boot.framework.config;


import com.xbw.spring.boot.framework.ws.AxisConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@ConditionalOnMissingBean(AxisConfig.class)
@Configuration
@ServletComponentScan("com.xbw.spring.boot")
public class WSConfig {

}