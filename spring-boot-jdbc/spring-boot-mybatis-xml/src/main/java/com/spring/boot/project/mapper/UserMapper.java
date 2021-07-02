package com.spring.boot.project.mapper;

import com.spring.boot.project.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int insert(User user);

    void update(User user);

//    void delete(Long id);

    User findByUserCode(String userCode);

    User findById(Long id);

    List<Map> findToMap();
}
