package com.iftm.serverlogs.models;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

public class Post {
    private String title;
    @Field("author_name")
    private String authorName;
    private String body;
    private List<Tag> tags;


    public Post(String title, String authorName, String body, List<Tag> tags) {
        this.title = title;
        this.authorName = authorName;
        this.body = body;
        this.tags = tags;
    }

    public Post() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
