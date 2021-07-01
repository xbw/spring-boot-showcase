package com.spring.boot.project.controller;

import com.spring.boot.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DataSource dataSource;
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
    public String jndi(Locale locale, Model model) {
        try {
            logger.debug("dataSource -> {}, connection -> {}", dataSource.getClass().getName(), dataSource.getConnection());
            ResultSet rs = dataSource.getConnection().createStatement().executeQuery("select * from INFORMATION_SCHEMA.TABLES");
            while (rs.next()) {
                logger.info("TABLE_NAME -> {}.{}.{}", rs.getString("TABLE_CATALOG"), rs.getString("TABLE_SCHEMA"), rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            logger.error("SQLException -> ", e);
        }
        return "ok";
    }

    @RequestMapping("/user")
    public List<Map<String, Object>> jndiByDataSource(Locale locale, Model model) {
        return userService.findAll();
    }
}