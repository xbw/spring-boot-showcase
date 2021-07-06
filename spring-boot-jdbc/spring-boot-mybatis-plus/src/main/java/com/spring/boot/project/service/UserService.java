package com.spring.boot.project.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.boot.project.mapper.UserMapper;
import com.spring.boot.project.model.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Autowired
    private SqlSession sqlSession;

    public List<Map> findToMap() {
        return sqlSession.selectList("com.spring.boot.project.mapper.UserMapper.findToMap");
    }
}
