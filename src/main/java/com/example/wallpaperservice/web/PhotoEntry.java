package com.example.wallpaperservice.web;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "media_library")
public class PhotoEntry {
    @Id
    String id;
    String title;
    byte[] media;
    Date uploadTime;
    String category;
    int rating;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public byte[] getMedia() {
        return media;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public String getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
