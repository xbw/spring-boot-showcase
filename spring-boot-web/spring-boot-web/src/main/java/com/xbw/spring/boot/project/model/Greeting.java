package com.xbw.spring.boot.project.model;

public class Greeting {

    private final long id;
    private final String message;

    public Greeting(long id, String message) {
        this.id = id;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}