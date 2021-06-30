package com.spring.boot.jdbc;

import com.spring.boot.project.service.UserDynamicService;
import com.spring.boot.project.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dynamic")
class JDBCDynamicTests extends JDBCTests{

    @Autowired
    private UserService userService;
    @Autowired
    private UserDynamicService userDynamicService;


    @Test
    @Order(1)
    void userService() {
        printJSON(userService.findAll());
    }

    @Test
    @Order(2)
    void findAllByDefault() {
        printJSON(userService.findAll());
    }

    @Test
    @Order(3)
    void findAllByPrimary() {
        printJSON(userService.findAllByPrimary());
    }

    @Test
    @Order(4)
    void findAllBySecondary() {
        printJSON(userService.findAllBySecondary());
    }

    @Test
    @Order(5)
    void userPrimaryService() {
        printJSON(userDynamicService.findAll());
    }

    @Override
    void dataSource() {
    }
}