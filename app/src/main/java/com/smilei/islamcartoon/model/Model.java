package com.smilei.islamcartoon.model;

public class Model {
    public String videoId, title, uri;
    public int image;

    public Model() {
    }

    public Model(String videoId, String title, String uri, int image) {
        this.videoId = videoId;
        this.title = title;
        this.uri = uri;
        this.image = image;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
