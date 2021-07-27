package com.xbw.spring.boot.jdbc;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

@TestMethodOrder(MethodOrderer.MethodName.class)
class JDBCDynamicMultiTests extends JDBCTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;
    @Autowired
    @Qualifier("secondJdbcTemplate")
    private JdbcTemplate secondJdbcTemplate;

    @Test
    void findAll() {
        printDS(jdbcTemplate.getDataSource());
    }

    @Test
    void findAllByPrimary() {
        printDS(primaryJdbcTemplate.getDataSource());
    }

    @Test
    void findAllBySecondary() {
        printDS(secondJdbcTemplate.getDataSource());
    }

    @Override
    void dataSource() {
    }
}