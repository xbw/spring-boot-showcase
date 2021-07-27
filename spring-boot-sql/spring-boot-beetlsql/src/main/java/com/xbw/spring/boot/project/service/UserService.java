package com.xbw.spring.boot.project.service;

import com.xbw.spring.boot.project.model.User;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    SQLManager sqlManager;

    public void insert(User entity) {
        sqlManager.insert(entity);
    }

    public int updateById(User entity) {
        return sqlManager.updateById(entity);
    }

    public User single(Object key) {
        return sqlManager.single(User.class, key);
    }

    public User unique(Object key) {
        return sqlManager.unique(User.class, key);
    }

    public int deleteById(Object key) {
        return sqlManager.deleteById(User.class, key);
    }

    public List<User> all() {
        return sqlManager.all(User.class);
    }

}
