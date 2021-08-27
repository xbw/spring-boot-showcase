package com.xbw.spring.boot.framework.quartz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration
 * @see org.springframework.boot.autoconfigure.quartz.QuartzProperties
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private Scheduler scheduler;

}
