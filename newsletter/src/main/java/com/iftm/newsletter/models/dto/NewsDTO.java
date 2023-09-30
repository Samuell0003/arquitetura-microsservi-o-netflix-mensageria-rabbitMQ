package com.iftm.newsletter.models.dto;

import com.iftm.newsletter.models.News;
import com.iftm.newsletter.models.Post;

import java.io.Serializable;
import java.util.List;

public class NewsDTO implements Serializable {
    private String id;
    private String title;
    private String date;
    private String editorName;
    private List<Post> posts;

    public NewsDTO(News news) {
        this.id = news.getId().toString();
        this.title = news.getTitle();
        this.date = news.getDate();
        this.editorName = news.getEditorName();
        this.posts = news.getPosts();
    }

    public NewsDTO() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEditorName() {
        return editorName;
    }

    public void setEditorName(String editorName) {
        this.editorName = editorName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
