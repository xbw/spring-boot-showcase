package com.xbw.spring.boot.project.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xbw.spring.boot.project.mapper.UserMapper;
import com.xbw.spring.boot.project.model.User;
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
        return sqlSession.selectList("UserMapper.findToMap");
    }
}
