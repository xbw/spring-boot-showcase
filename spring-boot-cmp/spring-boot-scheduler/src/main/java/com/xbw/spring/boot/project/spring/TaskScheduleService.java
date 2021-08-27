package com.xbw.spring.boot.project.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * https://blog.csdn.net/applebomb/article/details/52400154
 * @author xbw
 */
@Service
public class TaskScheduleService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskScheduler taskScheduler;

    public void taskScheduler(int n) {
        for (int i = 0; i < n; i++) {
            int ii = i;
            taskScheduler.schedule(() -> logger.debug("task-scheduler-" + ii), new CronTrigger("0/7 * * * * ?"));
        }
    }

    //@Scheduled(fixedDelayString = "3000", initialDelay = 3 * 1000)
//    @Scheduled(fixedDelay = 3 * 1000, initialDelay = 3 * 1000)
    public void fixedDelay() {
        print("fixedDelay", 6);
    }

    //@Scheduled(fixedRateString = "${spring.scheduled.fixed-rate-string}", initialDelay = 5 * 1000)
    @Scheduled(fixedRate = 5 * 1000, initialDelay = 5 * 1000)
//    @org.springframework.scheduling.annotation.Async
    public void fixedRate() {
        print("fixedRate", 10);
    }

    //    @Scheduled(cron = "0/7 * * * * ?")
    public void cron() {
        print("cron", 14);
    }


    private void print(String method, int bound) {
        logger.debug("{} begins at {}", method, FORMATTER.format(LocalTime.now()));
        try {
            int i = new Random().nextInt(bound);
            Thread.sleep(i * 1000);
            logger.debug("{} execute {}s", method, i);
        } catch (InterruptedException e) {
            logger.error("{} has been interrupted!", method, e);
            return;
        } finally {
            logger.debug("{} completed at {}\n", method, FORMATTER.format(LocalTime.now()));
        }
    }
}