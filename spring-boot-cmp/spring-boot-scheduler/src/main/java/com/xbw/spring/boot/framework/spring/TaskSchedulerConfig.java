package com.xbw.spring.boot.framework.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * https://spring.io/guides/gs/scheduling-tasks/
 * https://www.baeldung.com/spring-task-scheduler
 * @author xbw
 * @see org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration
 * @see org.springframework.boot.autoconfigure.task.TaskSchedulingProperties
 */
@EnableScheduling
//@EnableAsync
@Configuration
public class TaskSchedulerConfig {
    @Autowired
    TaskScheduler taskScheduler;
}
