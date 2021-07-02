package com.spring.boot.project.controller;

import com.spring.boot.project.dao.UserDao;
import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.mapper.UsersMapper;
import com.spring.boot.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MybatisXmlController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UsersMapper usersMapper;

    @RequestMapping("user/add")
    public User addUser() {
        User user = new User("user", "user");
        userMapper.insert(user);
        return user;
    }

    @RequestMapping("user/query/{id}")
    public User queryUser(@PathVariable Long id) {
        return userDao.findById(id);
    }

    @RequestMapping("user/query")
    public List<User> queryUser() {
        return usersMapper.findAll();
    }

}
