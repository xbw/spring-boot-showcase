package com.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * use {@code @MapperScan("com.spring.boot.**.mapper")}
 * or {@code @MapperScans({@MapperScan("com.spring.boot.**.mapper"),@MapperScan("com.spring.boot.**.dao")})}
 *
 * @see MapperScan
 * @see MapperScans
 */
@SpringBootApplication
public class MybatisXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisXmlApplication.class, args);
    }

}
