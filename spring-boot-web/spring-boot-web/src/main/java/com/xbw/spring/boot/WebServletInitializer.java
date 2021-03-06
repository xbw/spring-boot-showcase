package com.xbw.spring.boot;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.traditional-deployment
 */
public class WebServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        String msg = String.format("Started Application by %s %s", Thread.currentThread().getStackTrace()[1].getClassName(), System.currentTimeMillis());
        logger.info(msg);
        return application.sources(WebApplication.class);
    }

}
