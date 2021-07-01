package com.spring.boot.project.service;

import com.spring.boot.project.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public List<Map<String, Object>> findAll() {
        return userDao.findAll();
    }

}
