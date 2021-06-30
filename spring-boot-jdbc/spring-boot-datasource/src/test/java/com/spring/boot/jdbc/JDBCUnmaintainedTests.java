package com.spring.boot.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.sql.DataSource;

class JDBCUnmaintainedTests extends JDBCTests {

    @Autowired
    @Qualifier("proxoolDataSource")
    DataSource proxoolDataSource;

    @Autowired
    @Qualifier("bonecpDataSource")
    DataSource bonecpDataSource;


    @Test
    void proxoolDataSource() {
        printDS(proxoolDataSource);
    }


    @Test
    void bonecpDataSource() {
        printDS(bonecpDataSource);
    }

}