package com.xbw.spring.boot.framework.runner;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author xbw
 */
@Component
public class ApplicationListenerImpl implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * @param event
     * @see org.springframework.context.ApplicationEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("The ApplicationListenerImpl use ApplicationListener start to initialize ...");
        new Thread(() -> {
            throw new RuntimeException();// Safe mode
        }).start();
    }
}
