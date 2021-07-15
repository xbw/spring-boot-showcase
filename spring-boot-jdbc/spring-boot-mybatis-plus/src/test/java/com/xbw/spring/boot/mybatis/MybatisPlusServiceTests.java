package com.xbw.spring.boot.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.xbw.spring.boot.project.model.User;
import com.xbw.spring.boot.project.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class MybatisPlusServiceTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    void print(Object object) {
        try {
            logger.info(new JsonMapper().writeValueAsString(object));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException ->", e);
            e.printStackTrace();
        }
    }

    @Test
    void insert() {
        User user = new User("user", "user");
        userService.save(user);
        print(user);
    }

    @Test
    void update() {
        User user = userService.getById(1L);
        print(user);
        if (user != null) {
            user.setUserCode("test");
            user.setUserName("test");
            userService.updateById(user);
            Assertions.assertEquals("test", userService.getById(1L).getUserName());
        } else {
            Assertions.assertNull(user);
        }
    }

    @Test
    void findById() {
        Long id = new Long(new Random().nextInt(20));
        print(userService.getById(id));
    }

    @Test
    void findByCode() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", "test");
        print(userService.listByMap(map));
    }

    /**
     * @see com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory
     */
    @Test
    void findToMap() {
        print(userService.findToMap());
        print(userService.listMaps());
    }

    @Test
    void findAll() {
        print(userService.list());
    }

    @Test
    void delete() {
        userService.removeById(9L);
    }

}