package com.xbw.spring.boot.project.jdk;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JDKScheduler {
    public static void main(String[] args) {
//        schedule();
//        scheduleAtFixedRate();
        scheduledExecutorService();
    }

    public static void schedule() {
        Timer timer = new Timer();
        System.out.println(new Date());
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("schedule(TimerTask task, long delay): " + new Date());
            }
        }, 2000);// 设定指定的时间time,此处为2000毫秒

        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("schedule(TimerTask task, long delay, long period): " + new Date());
            }
        }, 1000, 2000);
    }

    public static void scheduleAtFixedRate() {
        Timer timer = new Timer();
        System.out.println(new Date());
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("scheduleAtFixedRate(TimerTask task, long delay, long period): " + new Date());
            }
        }, 1000, 2000);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("scheduleAtFixedRate(TimerTask task,Date firstTime,long period): " + new Date());
            }
        }, time, 1000 * 60 * 60 * 24);
    }

    public static void scheduledExecutorService() {
        Runnable runnable = () -> {
            System.out.println("ScheduledExecutorService: " + new Date());
        };
        //ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1);
        System.out.println(new Date());
        service.scheduleAtFixedRate(runnable, 4, 2, TimeUnit.SECONDS);
    }
}
