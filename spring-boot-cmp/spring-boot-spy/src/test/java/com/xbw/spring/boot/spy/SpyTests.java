package com.xbw.spring.boot.spy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class SpyTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void test() throws JsonProcessingException {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME = ?", "USERS");
        logger.debug(new JsonMapper().writeValueAsString(list));
        list = jdbcTemplate.queryForList("select * from INFORMATION_SCHEMA.USERS");
        logger.debug(new JsonMapper().writeValueAsString(list));
        list = jdbcTemplate.queryForList("select * from USERS");
        logger.debug(new JsonMapper().writeValueAsString(list));
    }
}
