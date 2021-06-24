package com.spring.boot.project.dynamic;

import com.spring.boot.project.model.User;
import org.beetl.sql.annotation.entity.Table;
import org.beetl.sql.annotation.entity.TargetSQLManager;

@Table(name = "user")
@TargetSQLManager("sqlManager2")
public class UserDynamicTwo extends User {
    public UserDynamicTwo() {
        super();
    }

    public UserDynamicTwo(String userCode, String userName) {
        super(userCode, userName);
    }

    public UserDynamicTwo(Long id, String userCode, String userName) {
        super(id, userCode, userName);
    }
}
