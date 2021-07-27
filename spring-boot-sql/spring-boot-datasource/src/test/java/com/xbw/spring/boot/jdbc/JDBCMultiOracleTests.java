package com.xbw.spring.boot.jdbc;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

/**
 * required : spring.profiles.include: multi
 */
@ActiveProfiles({"oracle"})
class JDBCMultiOracleTests extends JDBCMultiTests {

    @Autowired
    @Qualifier("oracleDataSource")
    DataSource oracleDataSource;


    @Test
    @Order(10)
    @Override
    void oracleDataSource() {
        printDS(oracleDataSource);
    }

}