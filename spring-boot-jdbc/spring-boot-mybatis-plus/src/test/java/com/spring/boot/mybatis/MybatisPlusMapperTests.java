package com.spring.boot.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.model.User;
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
class MybatisPlusMapperTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;

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
        userMapper.insert(user);
        print(user);
    }

    @Test
    void update() {
        User user = userMapper.selectById(1L);
        print(user);
        if (user != null) {
            user.setUserCode("test");
            user.setUserName("test");
            userMapper.updateById(user);
            Assertions.assertEquals("test", userMapper.selectById(1L).getUserName());
        } else {
            Assertions.assertNull(user);
        }
    }

    @Test
    void findById() {
        Long id = new Long(new Random().nextInt(20));
        print(userMapper.selectById(id));
    }

    @Test
    void findByCode() {
        Map<String, Object> map = new HashMap<>();
        map.put("user_code", "test");
        print(userMapper.selectByMap(map));
    }

    @Test
    void findToMap() {
        print(userMapper.findToMap());
        print(userMapper.findToMapByAnnotation());
        print(userMapper.selectMaps(null));
    }

    @Test
    void findAll() {
        print(userMapper.selectList(null));
    }

    @Test
    void delete() {
        userMapper.deleteById(9L);
    }

}