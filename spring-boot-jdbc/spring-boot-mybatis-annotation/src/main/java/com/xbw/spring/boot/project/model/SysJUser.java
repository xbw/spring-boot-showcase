package com.xbw.spring.boot.project.model;

import java.io.Serializable;


public class SysJUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long userId;
    private String userCode;
    private String userName;

    public SysJUser() {
        super();
    }

    public SysJUser(String userCode, String userName) {
        this.userCode = userCode;
        this.userName = userName;
    }

    public SysJUser(Long userId, String userCode, String userName) {
        this.userId = userId;
        this.userCode = userCode;
        this.userName = userName;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
