package com.iftm.newsletter.models.dto;

import java.io.Serializable;
import java.util.Date;

public class LogDTO<T> implements Serializable {
    private String action;
    private Date date = new Date();
    private T obj;
    private String classType;

    public LogDTO(String action, Date date, T t, String classType) {
        this.action = action;
        this.date = date;
        this.obj = t;
        this.classType = classType;
    }

    public LogDTO(String action, T t) {
        this.action = action;
        this.obj = t;
        this.classType = t.getClass().toString();
    }



    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
