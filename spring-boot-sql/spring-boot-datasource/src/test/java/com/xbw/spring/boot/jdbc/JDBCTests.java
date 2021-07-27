package com.xbw.spring.boot.jdbc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JDBCTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DataSource dataSource;

    void printDS(DataSource dataSource) {
        try {
            logger.debug("dataSource -> {}, connection -> {}", dataSource.getClass().getName(), dataSource.getConnection());
            List<Map<String, Object>> list = new JdbcTemplate(dataSource).queryForList("select * from sys_j_user");
            printJSON(list);
        } catch (SQLException e) {
            logger.error("SQLException -> ", e);
        }
    }

    void printJSON(Object o) {
        try {
            logger.info(new JsonMapper().writeValueAsString(o));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException -> ", e);
        }
    }

    @Test
    @Order(1)
    void dataSource() {
        printDS(dataSource);
    }

}