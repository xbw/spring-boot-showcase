package com.spring.boot.project.dao;

import com.spring.boot.project.model.User;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * 当md在BeetlSql配置的根目录下时会自动匹配，非标准路径可以配合SqlResource 使用
 *
 * @see SqlResource 使用
 */
//@SqlResource("user")
public interface UserDao extends BaseMapper<User> {

    User findByUserCode(String userCode);

    @Sql("delete from user where user_code=?")
    int deleteByUserCode(String UserCode);
}
