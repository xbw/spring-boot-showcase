package com.xbw.spring.boot;

import com.xbw.spring.boot.project.spel.SpELBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ELApplicationTests {
    @Autowired
    SpELBean spELBean;

    @Test
    void contextLoads() {
    }

    @Test
    void spEL() {
        System.out.println("App Name: " + spELBean.getAppName());
        System.out.println("Java Version: " + spELBean.getJavaVersion());
        System.out.println("Pi: " +spELBean.getPi());
        System.out.println("Random: " +spELBean.getRandom());
        System.out.println("Separator: " +spELBean.getSeparator());
    }
}
