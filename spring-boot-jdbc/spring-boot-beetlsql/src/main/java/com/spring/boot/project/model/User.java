package com.spring.boot.project.model;

import org.beetl.sql.annotation.entity.AutoID;
import org.beetl.sql.annotation.entity.Column;

import java.io.Serializable;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /**
     * other @AssignID("") , @SeqID(name="xxx_seq")
     *
     * @return
     */
    @AutoID
    @Column("user_id")
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
