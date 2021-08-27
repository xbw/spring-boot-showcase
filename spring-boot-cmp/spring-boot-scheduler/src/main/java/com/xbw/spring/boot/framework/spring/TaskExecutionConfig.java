package com.xbw.spring.boot.framework.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

/**
 * @author xbw
 * @see org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
 * @see org.springframework.boot.autoconfigure.task.TaskExecutionProperties
 */
@Configuration
public class TaskExecutionConfig {
    @Autowired
    TaskExecutor taskExecutor;

}
