package com.example.wallpaperservice.web;

import java.util.Date;

public class ShortDescPhoto{
    String id;
    String title;
    Date uploadTime;
    String category;
    int rating;
    String size;
    String uri;

    public ShortDescPhoto(String id, String title, Date uploadTime, String category, int rating, String size, String uri) {
        this.id = id;
        this.title = title;
        this.uploadTime = uploadTime;
        this.category = category;
        this.rating = rating;
        this.size = size;
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
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

    public String getSize() {
        return size;
    }

    public String getUri() {
        return uri;
    }
}
