package com.iftm.serverlogs.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
@Document(collection = "logs_news")
public class Logs<T> implements Serializable {
    @Id
    private ObjectId id;
    private String action;
    private Date date;
    private T obj;
    @Field("class_type")
    private String classType;

    public Logs(String action, Date date, T obj, String classType) {
        this.action = action;
        this.date = date;
        this.obj = obj;
        this.classType = classType;
    }

    public Logs() { }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
