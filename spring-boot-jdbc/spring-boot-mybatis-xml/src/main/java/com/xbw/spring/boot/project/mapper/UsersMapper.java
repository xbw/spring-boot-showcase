package com.xbw.spring.boot.project.mapper;

import com.xbw.spring.boot.project.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {

    List<User> findAll();

    List<Map> findToMap();
}
