package com.spring.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class SpringBootWebOldServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        String msg = String.format("Started Application by %s %s", Thread.currentThread().getStackTrace()[1].getClassName(), System.currentTimeMillis());
        logger.info(msg);
        return application.sources(SpringBootWebOldApplication.class);
    }

}