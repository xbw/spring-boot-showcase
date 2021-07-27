package com.xbw.spring.boot.project.multi.mapper.secondary;

import com.xbw.spring.boot.project.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MultiUsersMapper {

    List<User> findAll();
}
