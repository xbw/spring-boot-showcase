package com.xbw.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * https://gitee.com/baomidou/mybatis-plus-samples
 * <p>
 * use {@code @MapperScan("com.xbw.spring.boot.**.mapper")}
 * or {@code @MapperScans({@MapperScan("com.xbw.spring.boot.**.mapper"),@MapperScan("com.xbw.spring.boot.**.dao")})}
 *
 * @see MapperScan
 * @see MapperScans
 */
@SpringBootApplication
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }

}
