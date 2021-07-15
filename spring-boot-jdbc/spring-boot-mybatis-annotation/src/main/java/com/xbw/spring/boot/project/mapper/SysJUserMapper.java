package com.xbw.spring.boot.project.mapper;

import com.xbw.spring.boot.framework.mybatis.CamelKeyMap;
import com.xbw.spring.boot.project.model.SysJUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

@Mapper
public interface SysJUserMapper {

    @Insert("INSERT INTO sys_j_user(user_code,user_name) VALUES(#{userCode},#{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(SysJUser user);

    @Update("UPDATE sys_j_user SET user_code=#{userCode},user_name=#{userName} WHERE user_id =#{userId}")
    void update(SysJUser user);

    @Delete("DELETE FROM sys_j_user WHERE user_id =#{userId}")
    void delete(Long userId);

    @Select("SELECT * FROM sys_j_user")
    @Results(id = "userResult", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name")
    })
    List<SysJUser> findAll();

    /**
     * https://mybatis.org/mybatis-3/zh/java-api.html @ResultType
     *
     * @param handler
     */
    @Select("SELECT * FROM sys_j_user")
    @ResultType(SysJUser.class)
    void findAllByHandler(ResultHandler handler);

    @Select("SELECT * FROM sys_j_user WHERE user_code = #{userCode} LIMIT 1")
    @ResultMap("userResult")
    SysJUser findByUserCode(@Param("userCode") String userCode);

    @Select("SELECT * FROM sys_j_user WHERE user_id = #{userId}")
    SysJUser findByUserId(Long userId);

    @Select("SELECT * FROM sys_j_user")
    List<CamelKeyMap> findToMap();

}

