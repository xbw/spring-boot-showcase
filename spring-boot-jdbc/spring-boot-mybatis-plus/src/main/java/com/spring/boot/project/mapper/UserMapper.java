package com.spring.boot.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.boot.framework.mybatis.CamelKeyMap;
import com.spring.boot.project.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Map> findToMap();

    @Select("SELECT * FROM sys_j_user")
    List<CamelKeyMap> findToMapByAnnotation();
}
