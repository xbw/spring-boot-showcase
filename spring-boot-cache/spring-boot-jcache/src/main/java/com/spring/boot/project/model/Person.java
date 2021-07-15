package com.spring.boot.project.model;


import java.io.Serializable;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String nickName;
    private String birthday;

    public Person() {
        super();
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(String userName, String nickName, String birthday) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.birthday = birthday;
    }

    public Person(Long id, String userName, String nickName, String birthday) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", birthday='" + birthday + "}@" + hashCode();
    }
}
