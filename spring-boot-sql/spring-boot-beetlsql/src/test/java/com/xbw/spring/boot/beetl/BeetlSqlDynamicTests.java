package com.xbw.spring.boot.beetl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbw.spring.boot.project.dynamic.UserDynamic;
import com.xbw.spring.boot.project.dynamic.UserDynamicTwo;
import com.xbw.spring.boot.project.model.User;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.SqlId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class BeetlSqlDynamicTests {
    @Autowired
    private SQLManager sqlManager;


    @Test
    void testInsert() throws Exception {
        User user = new User("user", "user");
        user = new UserDynamic("user1", "user1");
        sqlManager.insert(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));
        user = new UserDynamicTwo("user2", "user2");
        sqlManager.insert(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));
    }

    @Test
    void testUpdate() throws Exception {
        User user = sqlManager.single(UserDynamic.class, 1l);
        if (user != null) {
            user.setUserCode("test1");
            user.setUserName("test1");
            sqlManager.updateById(user);
        }

        user = sqlManager.single(UserDynamicTwo.class, 1l);
        if (user != null) {
            user.setUserCode("test2");
            user.setUserName("test2");
            sqlManager.updateById(user);
        }

    }

    @Test
    void testFindAll() {
        List list = sqlManager.all(UserDynamic.class);
        list = sqlManager.all(UserDynamicTwo.class);
    }

    @Test
    void testMd() throws JsonProcessingException {
        List list = sqlManager.select(SqlId.of("user.findAll"), Map.class);
        System.out.println(new ObjectMapper().writeValueAsString(list));

        list = sqlManager.select(SqlId.of("user.findAll"), UserDynamic.class);
        System.out.println(new ObjectMapper().writeValueAsString(list));
        list = sqlManager.select(SqlId.of("user.findAll"), UserDynamicTwo.class);
        System.out.println(new ObjectMapper().writeValueAsString(list));
    }

    @Test
    void testDelete() throws Exception {
        // use @Column("user_id")
        sqlManager.deleteById(UserDynamic.class, 9l);
        sqlManager.deleteById(UserDynamicTwo.class, 9l);
    }

}