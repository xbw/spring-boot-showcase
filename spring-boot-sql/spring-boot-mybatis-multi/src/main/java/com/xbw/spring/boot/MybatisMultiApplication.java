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
@MapperScan(basePackages = "com.xbw.spring.boot.**.project.mapper", sqlSessionTemplateRef  = "sqlSessionTemplate")
@MapperScan(basePackages = "com.xbw.spring.boot.**.multi.mapper.primary", sqlSessionTemplateRef  = "primarySST")
@MapperScan(basePackages = "com.xbw.spring.boot.**.multi.mapper.secondary", sqlSessionTemplateRef  = "secondarySST")
@SpringBootApplication
public class MybatisMultiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMultiApplication.class, args);
    }

}
