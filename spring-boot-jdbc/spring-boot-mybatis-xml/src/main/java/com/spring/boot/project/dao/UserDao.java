package com.spring.boot.project.dao;

import com.spring.boot.project.model.User;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

//    private final SqlSession sqlSession;
//
//    public UserDao(SqlSession sqlSession) {
//        this.sqlSession = sqlSession;
//    }

    @Autowired
    private SqlSession sqlSession;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public User findById(Long id) {
        return sqlSession.selectOne("com.spring.boot.project.mapper.UserMapper.findById", id);
    }

    public void delete(Long id) {
        sqlSessionTemplate.delete("com.spring.boot.project.mapper.UserMapper.delete", id);
    }

    /**
     * https://mybatis.org/mybatis-3/zh/java-api.html
     *
     * @param handler
     */
    public void findAllByHandler(ResultHandler handler) {
        sqlSessionTemplate.select("com.spring.boot.project.mapper.UsersMapper.findAll", handler);
    }
}
