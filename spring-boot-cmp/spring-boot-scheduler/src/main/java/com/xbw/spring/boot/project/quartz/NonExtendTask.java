package com.xbw.spring.boot.project.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xbw
 */
@Component
public class NonExtendTask {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void execute() {
        logger.debug("Non-Extend-Task-{}", formatter.format(LocalDateTime.now()));
    }
}
