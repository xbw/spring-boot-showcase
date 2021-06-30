package com.spring.boot.project.controller;

import com.spring.boot.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserService userService;

    /**
     * https://www.h2database.com/html/systemtables.html
     *
     * @param locale
     * @param model
     * @return
     */
    @RequestMapping("/jndi")
    public List<Map<String, Object>> jndi(Locale locale, Model model) {
        try {
            DataSource dataSource = jdbcTemplate.getDataSource();
            logger.debug("dataSource -> {}, connection -> {}", dataSource.getClass().getName(), dataSource.getConnection());
        } catch (SQLException e) {
            logger.error("SQLException -> ", e);
        }
        return jdbcTemplate.queryForList("select * from INFORMATION_SCHEMA.TABLES");
    }

    @RequestMapping("/user")
    public List<Map<String, Object>> jndiByDataSource(Locale locale, Model model) {
        return userService.findAll();
    }
}