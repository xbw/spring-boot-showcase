package com.xbw.spring.boot.project.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("select * from sys_j_user");
    }

}
