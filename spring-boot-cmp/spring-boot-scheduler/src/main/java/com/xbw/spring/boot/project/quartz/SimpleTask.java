package com.xbw.spring.boot.project.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xbw
 */
public class SimpleTask extends QuartzJobBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.debug("Simple-Task-{}", formatter.format(LocalDateTime.now()));
    }
}