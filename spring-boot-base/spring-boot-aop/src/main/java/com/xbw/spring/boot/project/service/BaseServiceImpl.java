package com.xbw.spring.boot.project.service;

import com.xbw.spring.boot.framework.aspectj.Aop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author xbw
 */
@Service
@Aop
public class BaseServiceImpl implements BaseService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String hello() {
        String str = "Hello World";
        logger.info(str);
        return str;
    }

    public void annotation() {
        logger.info(new Exception().getStackTrace()[0].getMethodName());
    }
}
