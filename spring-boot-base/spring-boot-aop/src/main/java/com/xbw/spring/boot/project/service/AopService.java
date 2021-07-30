package com.xbw.spring.boot.project.service;

import com.xbw.spring.boot.framework.aspectj.Aop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xbw
 */
@Service
public class AopService implements BaseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${aop.name:World}")
    private String name;

    public String hello() {
        String str = "Hello " + this.name;
        logger.info(str);
        return str;
    }

    public String hello(String name) {
        String str = "Hello " + name;
        logger.info(str);
        return str;
    }

    @Aop
    public void annotation() {
        logger.info(new Exception().getStackTrace()[0].getMethodName());
    }

}
