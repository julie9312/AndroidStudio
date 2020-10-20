package com.julie.photos.model;

public class Photo {
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Photo(String title, String url, String thumbnailUrl) {
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}