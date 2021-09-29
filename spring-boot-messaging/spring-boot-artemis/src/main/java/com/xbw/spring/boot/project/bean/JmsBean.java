package com.xbw.spring.boot.project.bean;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xbw
 */
public class JmsBean implements Serializable {

    private final static AtomicLong ATOMIC_LONG = new AtomicLong();
    private Long id;
    private String text;

    public JmsBean() {
        this.id = ATOMIC_LONG.incrementAndGet();
    }

    public JmsBean(String text) {
        this.id = ATOMIC_LONG.incrementAndGet();
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JmsBean{id=" + id + ", text='" + text + "'}";
    }
}
