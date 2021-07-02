package com.spring.boot.project.mapper;

import com.spring.boot.framework.mybatis.CamelKeyMap;
import com.spring.boot.project.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users(user_code,user_name) VALUES(#{userCode},#{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "id"/*, keyColumn = "user_id"*/)
        // 表未指定主键时需要配置keyColumn
    int insert(User user);


    @Update("UPDATE users SET user_code=#{userCode},user_name=#{userName} WHERE user_id =#{id}")
    void update(User user);

    @Delete("DELETE FROM users WHERE user_id =#{id}")
    void delete(Long id);

    @Select("SELECT * FROM users")
    @Results(id = "userResult", value = {
            @Result(property = "id", column = "user_id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name")
    })
    List<User> findAll();

    @Select("SELECT * FROM users")
    @ResultMap("userResult")
    void findAllByHandler(ResultHandler handler);

    @Select("SELECT * FROM users WHERE user_code = #{userCode} LIMIT 1")
    @Results({
            @Result(property = "id", column = "user_id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name")
    })
    User findByUserCode(@Param("userCode") String userCode);

    @Select("SELECT * FROM users WHERE user_id = #{id}")
    @ResultMap("userResult")
    User findById(Long id);

    @Select("SELECT * FROM users")
    List<CamelKeyMap> findToMap();

}
