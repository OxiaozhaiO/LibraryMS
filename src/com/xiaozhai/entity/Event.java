package com.xiaozhai.entity;

import java.sql.Date;

public class Event {
    private int id;
    private String username;
    private String BorR;
    private String book;
    private Date time;

    public Event(){}
    public Event(String username, String BorR, String book, Date time) {
        this.id = id;
        this.username = username;
        this.BorR = BorR;
        this.book = book;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getBorR() {
        return BorR;
    }

    public String getBook() {
        return book;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBorR(String borR) {
        BorR = borR;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "["+time+"] "+username +" "+ BorR + "《"+book+"》"  ;
    }
}