package com.xbw.spring.boot.scheduler;

import com.xbw.spring.boot.project.spring.TaskExecutorService;
import com.xbw.spring.boot.project.spring.TaskScheduleService;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

/**
 * @author xbw
 */
@SpringBootTest
public class SchedulerTests {
    @Autowired
    TaskExecutorService taskExecutorService;
    @Autowired
    TaskScheduleService taskScheduleService;

    @Test
    void taskExecutor() {
        taskExecutorService.taskExecutor(10);
    }

    @Test
    void taskScheduler() {
        taskScheduleService.taskScheduler(5);
        Awaitility.await().atMost(60, TimeUnit.HOURS);
    }

    @Test
    void awaitility() {
        Awaitility.await().atMost(60, TimeUnit.HOURS);
    }
}
