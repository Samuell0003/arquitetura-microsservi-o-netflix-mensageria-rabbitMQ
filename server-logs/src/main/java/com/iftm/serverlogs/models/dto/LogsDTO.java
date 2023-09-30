package com.iftm.serverlogs.models.dto;

import com.iftm.serverlogs.models.Logs;

import java.io.Serializable;
import java.util.Date;

public class LogsDTO<T> implements Serializable {

    private String id;
    private String action;
    private Date date = new Date();
    private T obj;
    private String classType;

    public LogsDTO(String action, Date date, T obj, String classType) {
        this.action = action;
        this.obj = obj;
        this.date = date;
        this.classType = classType;
    }
    public LogsDTO(Logs logs) {
        this.id = logs.getId().toString();
        this.action = logs.getAction();
        this.date = logs.getDate();
        this.obj = (T) logs.getObj();
        this.classType = logs.getClassType();
    }

    public LogsDTO() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
