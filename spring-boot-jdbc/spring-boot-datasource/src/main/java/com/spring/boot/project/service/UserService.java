package com.spring.boot.project.service;

import com.spring.boot.framework.dynamic.annotation.DynamicDS;
import com.spring.boot.framework.dynamic.enums.DynamicDataSourceEnum;
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

    @DynamicDS(DynamicDataSourceEnum.DEFAULT)
    public List<Map<String, Object>> findAllByDefault() {
        return userDao.findAll();
    }

    @DynamicDS(DynamicDataSourceEnum.PRIMARY)
    public List<Map<String, Object>> findAllByPrimary() {
        return userDao.findAll();
    }

    @DynamicDS(DynamicDataSourceEnum.SECONDARY)
    public List<Map<String, Object>> findAllBySecondary() {
        return userDao.findAll();
    }
}
