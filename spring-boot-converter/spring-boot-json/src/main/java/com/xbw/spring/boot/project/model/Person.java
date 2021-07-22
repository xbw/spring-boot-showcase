package com.xbw.spring.boot.project.model;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String nickName;
    private String birthday;
    private Date date;
    private LocalDateTime localDateTime;

    public Person() {
        super();
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(String userName, String nickName, String birthday) {
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

    public Person(Long id, String userName, String nickName, String birthday, Date date, LocalDateTime localDateTime) {
        this.id = id;
        this.userName = userName;
        this.nickName = nickName;
        this.birthday = birthday;
        this.date = date;
        this.localDateTime = localDateTime;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
