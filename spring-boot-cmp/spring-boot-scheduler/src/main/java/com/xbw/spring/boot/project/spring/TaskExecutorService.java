package com.xbw.spring.boot.project.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author xbw
 */
@Service
public class TaskExecutorService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskExecutor taskExecutor;

    @Autowired
    public TaskExecutorService(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void taskExecutor(int n) {
        for (int i = 0; i < n; i++) {
            int ii = i;
            taskExecutor.execute(() -> {
                logger.debug("task-executor-" + ii);
            });
        }
    }

}