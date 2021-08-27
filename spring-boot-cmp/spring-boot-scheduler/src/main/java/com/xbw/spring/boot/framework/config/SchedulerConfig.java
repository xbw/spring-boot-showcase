package com.xbw.spring.boot.framework.config;

import com.xbw.spring.boot.project.quartz.CronTask;
import com.xbw.spring.boot.project.quartz.NonExtendTask;
import com.xbw.spring.boot.project.quartz.SimpleTask;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @author xbw
 */
@Configuration
public class SchedulerConfig {
    @Bean
    public MethodInvokingJobDetailFactoryBean nonExtendTaskJobDetail(NonExtendTask task) {
        MethodInvokingJobDetailFactoryBean factoryBean = new MethodInvokingJobDetailFactoryBean();
        factoryBean.setTargetObject(task);
        factoryBean.setTargetMethod("execute");
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean nonExtendTrigger(NonExtendTask task) {
        SimpleTriggerFactoryBean simpleTriggerBean = new SimpleTriggerFactoryBean();
        simpleTriggerBean.setJobDetail(nonExtendTaskJobDetail(task).getObject());
        simpleTriggerBean.setRepeatInterval(11000);
        return simpleTriggerBean;
    }

    @Bean
    public JobDetail simpleJob() {
        return JobBuilder.newJob(SimpleTask.class).withIdentity("simpleTask").storeDurably().build();
    }

    @Bean
    public Trigger simpleTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(13)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(simpleJob())
                .withIdentity("simpleTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public JobDetail cronJob() {
        return JobBuilder.newJob(CronTask.class).withIdentity("cronTask").storeDurably().build();
    }

    @Bean
    public Trigger cronTrigger() {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("*/17 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(cronJob())
                .withIdentity("cronTask")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
