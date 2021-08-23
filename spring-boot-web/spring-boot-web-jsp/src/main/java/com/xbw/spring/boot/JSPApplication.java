package com.xbw.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * https://stackoverflow.com/questions/29782915/spring-boot-jsp-404
 * https://my.oschina.net/u/989834/blog/1933558
 * Open independently in idea
 * or config idea working directory: $MODULE_DIR$
 * or move jsp to src/main/resources/META-INF/resources/WEB-INF/jsp
 * @author xbw
 */
@SpringBootApplication
public class JSPApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JSPApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(JSPApplication.class);
    }
}
