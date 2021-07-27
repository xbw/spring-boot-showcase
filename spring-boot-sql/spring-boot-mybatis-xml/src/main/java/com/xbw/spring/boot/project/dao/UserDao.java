package com.xbw.spring.boot.project.dao;

import com.xbw.spring.boot.project.model.User;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
        return sqlSession.selectOne("UserMapper.findById", id);
    }

    public void delete(Long id) {
        sqlSessionTemplate.delete("UserMapper.delete", id);
    }

    /**
     * https://mybatis.org/mybatis-3/zh/java-api.html
     *
     * @param handler
     */
    public void findAllByHandler(ResultHandler handler) {
        sqlSessionTemplate.select("UsersMapper.findAll", handler);
    }

    public List<Map> findToMap() {
        return sqlSessionTemplate.selectList("UsersMapper.findToMap");
    }
}
