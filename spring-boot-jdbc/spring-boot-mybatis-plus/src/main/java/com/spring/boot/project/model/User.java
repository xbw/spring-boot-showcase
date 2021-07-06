package com.spring.boot.project.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    private Long id;
    private String userCode;
    private String userName;

    public User() {
        super();
    }

    public User(String userCode, String userName) {
        this.userCode = userCode;
        this.userName = userName;
    }

    public User(Long id, String userCode, String userName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
