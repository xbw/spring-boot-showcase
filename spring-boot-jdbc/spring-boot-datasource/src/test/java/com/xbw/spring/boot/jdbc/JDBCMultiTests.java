package com.xbw.spring.boot.jdbc;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;

/**
 * required : spring.profiles.include: multi
 */
class JDBCMultiTests extends JDBCTests {

    @Autowired
    @Qualifier("tomcatDataSource")
    DataSource tomcatDataSource;
    @Autowired
    @Qualifier("dbcp2DataSource")
    DataSource dbcp2DataSource;
    @Autowired
    @Qualifier("ucpDataSource")
    DataSource ucpDataSource;
    @Autowired
    @Qualifier("druidDataSource")
    DataSource druidDataSource;
    @Autowired
    @Qualifier("tomcatDbcp2DataSource")
    DataSource tomcatDbcp2DataSource;
    @Autowired
    @Qualifier("c3p0DataSource")
    DataSource c3p0DataSource;
    @Autowired
    @Qualifier("simpleDriverDataSource")
    DataSource simpleDriverDataSource;
    @Autowired
    @Qualifier("driverManagerDataSource")
    DataSource driverManagerDataSource;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    boolean dbCheck() {
        return "oracle".equals(springProfilesActive);
    }

    @Test
    @Order(2)
    void tomcatDataSource() {
        printDS(tomcatDataSource);
    }

    @Test
    @Order(3)
    void dbcp2DataSource() {
        printDS(dbcp2DataSource);
    }

    @Test
    @Order(4)
    void ucpDataSource() {
        printDS(ucpDataSource);
    }

    @Test
    @Order(5)
    void druidDataSource() {
        printDS(druidDataSource);
    }

    @Test
    @Order(6)
    void tomcatDbcp2DataSource() {
        printDS(tomcatDbcp2DataSource);
    }

    @Test
    @Order(7)
    void c3p0DataSource() {
        printDS(c3p0DataSource);
    }

    @Test
    @Order(8)
    void simpleDriverDataSource() {
        printDS(simpleDriverDataSource);
    }

    @Test
    @Order(9)
    void driverManagerDataSource() {
        printDS(driverManagerDataSource);
    }

    @Test
    @Order(10)
    @EnabledIf("dbCheck")
    void oracleDataSource() {
        @Nested
        class NestedTests {
            @Autowired
            @Qualifier("oracleDataSource")
            DataSource oracleDataSource;

            @Test
            void oracleDataSource() {
                printDS(oracleDataSource);
            }
        }
    }
}