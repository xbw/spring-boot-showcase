package com.xbw.spring.boot.beetl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xbw.spring.boot.project.dao.UserDao;
import com.xbw.spring.boot.project.model.User;
import com.xbw.spring.boot.project.service.UserService;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeetlSqlTests {
    @Autowired
    SQLManager sqlManager;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


    @Test
    public void testInsert() throws Exception {
        User user = new User("user", "user");
        userDao.insert(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));
        userService.insert(user);
        System.out.println(new ObjectMapper().writeValueAsString(user));

        userDao.insert(user,true);
        System.out.println(new ObjectMapper().writeValueAsString(user));
        userService.insert(user,true);
        System.out.println(new ObjectMapper().writeValueAsString(user));
    }

    @Test
    public void testUpdate() throws Exception {
        User user = userDao.single(1l);
//        user = userDao.unique(1l);
        if (user != null) {
            user.setUserCode("test");
            user.setUserName("test");
            userDao.updateById(user);
            userService.updateById(user);
        }
    }

    @Test
    public void testFindAll() {
        userDao.all();
        userService.all();
    }

    @Test
    public void testMd() throws JsonProcessingException {
        List<User> users = sqlManager.select("user.findAll", User.class);
        System.out.println(new ObjectMapper().writeValueAsString(users));

        List<Map> list = sqlManager.select("user.findAll", Map.class);
        System.out.println(new ObjectMapper().writeValueAsString(list));
    }

    @Test
    public void testOthers() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(userDao.findByUserCode("test")));
        userDao.deleteByUserCode("test1");
    }

    @Test
    public void testPage() {
        PageQuery<User> page = sqlManager.query(User.class).page(1, 10);
        System.out.println(page.getList());
        System.out.println(page.getTotalRow());
        System.out.println(page.getTotalPage());
    }

    @Test
    public void testDelete() throws Exception {
        // use @Column("user_id")
        userDao.deleteById(9l);
        userDao.deleteByUserCode("beetlsql");
        userService.deleteById(9l);
    }

}