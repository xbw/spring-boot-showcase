package com.xbw.spring.boot.beetl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbw.spring.boot.project.mapper.UserMapper;
import com.xbw.spring.boot.project.model.User;
import com.xbw.spring.boot.project.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SqlId;
import org.beetl.sql.core.page.PageResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BeetlSqlTests {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;


    @Test
    void testInsert() throws Exception {
        User user = new User("user", "user");
        userMapper.insert(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));
        userService.insert(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));
    }

    @Test
    void testUpdate() throws Exception {
        User user = userMapper.single(1l);
//        user = userMapper.unique(1l);
        if (user != null) {
            user.setUserCode("test");
            user.setUserName("test");
            userMapper.updateById(user);
            userService.updateById(user);
        }
    }

    @Test
    void testFindAll() {
        userMapper.all();
        userService.all();
    }

    @Test
    void testMd() throws JsonProcessingException {
        List<User> users = sqlManager.select(SqlId.of("user.findAll"), User.class);
        System.out.println(new ObjectMapper().writeValueAsString(users));

        List<Map> list = sqlManager.select(SqlId.of("user.findAll"), Map.class);
        System.out.println(new ObjectMapper().writeValueAsString(list));
    }

    @Test
    void testOthers() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(userMapper.findByUserCode("test")));
        userMapper.deleteByUserCode("test1");
    }

    @Test
    void testPage() {
        PageResult<User> page = sqlManager.query(User.class).page(1, 10);
        System.out.println(page.getList());
        System.out.println(page.getTotalRow());
        System.out.println(page.getTotalPage());
    }

    @Test
    void testDelete() throws Exception {
        // use @Column("user_id")
        userMapper.deleteById(9l);
        userMapper.deleteByUserCode("beetlsql");
        userService.deleteById(9l);
    }

}