package com.spring.boot.mybatis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.spring.boot.project.mapper.SysJUserMapper;
import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.model.SysJUser;
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
class MybatisAnnotationTests {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysJUserMapper sysJUserMapper;
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
        SysJUser sysJUser = new SysJUser("sys_j_user", "sys_j_user");
        userMapper.insert(user);
        sysJUserMapper.insert(sysJUser);
        print(user);
        print(sysJUser);
    }

    @Test
    void update() {
        SysJUser sysJUser = sysJUserMapper.findByUserId(1L);
        print(sysJUser);
        if (sysJUser != null) {
            sysJUser.setUserCode("test");
            sysJUser.setUserName("test");
            sysJUserMapper.update(sysJUser);
            Assertions.assertEquals("test", sysJUserMapper.findByUserId(1L).getUserName());
        } else {
            Assertions.assertNull(sysJUser);
        }
    }

    @Test
    void findById() {
        Long id = new Long(new Random().nextInt(20));
        print(userMapper.findById(id));
        print(sysJUserMapper.findByUserId(id));
    }

    @Test
    void findByCode() {
        String code = "test";
        print(userMapper.findByUserCode(code));
        print(sysJUserMapper.findByUserCode(code));
    }

    @Test
    void findToMap() {
        print(userMapper.findToMap());
        print(sysJUserMapper.findToMap());
    }

    @Test
    void findAll() {
        print(userMapper.findAll());
        print(sysJUserMapper.findAll());
    }

    @Test
    void findAllByHandler() {
        ResultHandler handler = resultContext -> {
            print(resultContext.getResultObject());
        };
//        userMapper.findAllByHandler(handler);
        sysJUserMapper.findAllByHandler(handler);
    }

    @Test
    void delete() {
        sysJUserMapper.delete(9L);
    }

}