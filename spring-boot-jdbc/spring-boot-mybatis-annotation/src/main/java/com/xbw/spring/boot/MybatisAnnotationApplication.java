package com.xbw.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * use {@code @MapperScan("com.xbw.spring.boot.**.mapper")}
 * or {@code @MapperScans({@MapperScan("com.xbw.spring.boot.**.mapper"),@MapperScan("com.xbw.spring.boot.**.dao")})}
 *
 * @see MapperScan
 * @see MapperScans
 */
@SpringBootApplication
public class MybatisAnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisAnnotationApplication.class, args);
    }

}
