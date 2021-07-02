package com.spring.boot.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.mapper.UsersMapper;
import com.spring.boot.project.model.User;
import com.spring.boot.project.multi.mapper.primary.MultiUserMapper;
import com.spring.boot.project.multi.mapper.secondary.MultiUsersMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class MybatisMultiTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MultiUserMapper multiUserMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private MultiUsersMapper multiUsersMapper;


    void print(Object object) {
        try {
            logger.info(new JsonMapper().writeValueAsString(object));
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException ->", e);
            e.printStackTrace();
        }
    }

    @Test
    void insert() throws JsonProcessingException {
        User user = new User("user", "user");
        userMapper.insert(user);
        multiUserMapper.insert(user);
        print(user);
    }

    @Test
    void update() {
        User user = userMapper.findById(1L);
        print(user);
        if (user != null) {
            user.setUserCode("test");
            user.setUserName("test");
            userMapper.update(user);
            Assertions.assertEquals("test", userMapper.findById(1L).getUserName());
        } else {
            Assertions.assertNull(user);
        }
        user = multiUserMapper.findById(1L);
        print(user);
        if (user != null) {
            user.setUserCode("test1");
            user.setUserName("test1");
            multiUserMapper.update(user);
            Assertions.assertEquals("test1", multiUserMapper.findById(1L).getUserName());
        } else {
            Assertions.assertNull(user);
        }

    }

    @Test
    void findById() {
        Long id = new Long(new Random().nextInt(20));
        print(userMapper.findById(id));
        print(multiUserMapper.findById(id));
    }


    @Test
    void findByCode() {
        print(userMapper.findByUserCode("test"));
        print(multiUserMapper.findByUserCode("test"));
    }

    @Test
    void findToMap() {
        print(userMapper.findToMap());
        print(multiUserMapper.findToMap());
    }

    @Test
    void findAll() {
        print(usersMapper.findAll());
        print(multiUsersMapper.findAll());
    }

    @Test
    void delete() {
        userMapper.delete(9L);
        multiUserMapper.delete(9L);
    }

}