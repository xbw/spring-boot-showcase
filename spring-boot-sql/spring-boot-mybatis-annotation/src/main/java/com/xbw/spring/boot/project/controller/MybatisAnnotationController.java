package com.xbw.spring.boot.project.controller;

import com.xbw.spring.boot.project.mapper.UserMapper;
import com.xbw.spring.boot.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MybatisAnnotationController {
    @Autowired
    private UserMapper userMapper;


    @RequestMapping("user/add")
    public User addUser() {
        User user = new User("user", "user");
        userMapper.insert(user);
        return user;
    }

    @RequestMapping("user/query/{id}")
    public User queryUser(@PathVariable Long id) {
        return userMapper.findById(id);
    }

    @RequestMapping("user/query")
    public List<User> queryUser() {
        return userMapper.findAll();
    }

}
