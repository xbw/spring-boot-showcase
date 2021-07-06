package com.spring.boot.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.spring.boot.project.dao.UserDao;
import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.mapper.UsersMapper;
import com.spring.boot.project.model.User;
import org.apache.ibatis.session.ResultHandler;
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
class MybatisXmlTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserDao userDao;


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

    }

    @Test
    void findById() {
        Long id = new Long(new Random().nextInt(20));
        print(userMapper.findById(id));
        print(userDao.findById(id));
    }


    @Test
    void findByCode() {
        print(userMapper.findByUserCode("test"));
    }

    @Test
    void findToMap() {
        print(userMapper.findToMap());
        print(userDao.findToMap());
    }

    @Test
    void findAll() {
        print(usersMapper.findAll());
    }

    @Test
    void findAllByHandler() {
        ResultHandler handler = resultContext -> {
            print(resultContext.getResultObject());
        };
        userDao.findAllByHandler(handler);
    }

    @Test
    void delete() {
        userDao.delete(9L);
    }

}