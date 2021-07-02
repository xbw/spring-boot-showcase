package com.spring.boot.project.mapper;

import com.spring.boot.project.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UsersMapper {

    List<User> findAll();
}
