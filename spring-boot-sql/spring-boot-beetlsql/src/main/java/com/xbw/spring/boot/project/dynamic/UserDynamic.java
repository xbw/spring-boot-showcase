package com.xbw.spring.boot.project.dynamic;

import com.xbw.spring.boot.project.model.User;
import org.beetl.sql.annotation.entity.Table;
import org.beetl.sql.annotation.entity.TargetSQLManager;
@Table(name = "users")
@TargetSQLManager("sqlManager1")
public class UserDynamic extends User {
    public UserDynamic() {
    }

    public UserDynamic(String userCode, String userName) {
        super(userCode, userName);
    }

    public UserDynamic(Long id, String userCode, String userName) {
        super(id, userCode, userName);
    }
}
