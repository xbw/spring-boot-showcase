package com.xbw.spring.boot.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xbw.spring.boot.framework.mybatis.CamelKeyMap;
import com.xbw.spring.boot.project.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<Map> findToMap();

    @Select("SELECT * FROM sys_j_user")
    List<CamelKeyMap> findToMapByAnnotation();

    IPage<User> findPage(Page<?> page);
}
