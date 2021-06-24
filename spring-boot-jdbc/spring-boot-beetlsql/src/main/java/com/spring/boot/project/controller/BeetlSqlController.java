package com.spring.boot.project.controller;

import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.model.User;
import com.spring.boot.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeetlSqlController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;


    @RequestMapping("user/add")
    public User addUser() {
        User user = new User("user", "user");
        userMapper.insert(user);
        return user;
    }

    @RequestMapping("user/query/{id}")
    public User queryUser(@PathVariable Long id) {
        return userMapper.single(id);
//        return userMapper.unique(id);
    }

    @RequestMapping("user/query")
    public List<User> queryUser() {
        userService.all();
        return userMapper.all();
    }

}
