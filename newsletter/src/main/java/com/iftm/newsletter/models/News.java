package com.iftm.newsletter.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
@Document(collection = "news")
public class News implements Serializable {
    @Id
    private ObjectId id;
    private String title;
    private String date;
    @Field("editor_name")
    private String editorName;
    private List<Post> posts;


    public News(String title, String date, String editorName, List<Post> posts) {
        this.title = title;
        this.date = date;
        this.editorName = editorName;
        this.posts = posts;
    }

    public News() { }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (!Objects.equals(id, news.id)) return false;
        if (!Objects.equals(title, news.title)) return false;
        if (!Objects.equals(date, news.date)) return false;
        if (!Objects.equals(editorName, news.editorName)) return false;
        return Objects.equals(posts, news.posts);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (editorName != null ? editorName.hashCode() : 0);
        result = 31 * result + (posts != null ? posts.hashCode() : 0);
        return result;
    }
}
