package com.spring.boot.project.multiple;

import com.spring.boot.project.model.User;
import org.beetl.sql.mapper.BaseMapper;
import org.beetl.sql.mapper.annotation.Sql;
import org.beetl.sql.mapper.annotation.SqlResource;
import org.beetl.sql.mapper.annotation.Update;

/**
 * 当md在BeetlSql配置的根目录下时会自动匹配，非标准路径可以配合SqlResource 使用
 *
 * @see SqlResource 使用
 */

//@SqlResource("user")
public interface UserSecondaryMapper extends BaseMapper<User> {

    User findByUserCode(String userCode);

    @Update
    @Sql("delete from users where user_code=?")
    int deleteByUserCode(String UserCode);
}
