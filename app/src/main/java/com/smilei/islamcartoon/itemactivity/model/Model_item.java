package com.smilei.islamcartoon.itemactivity.model;

public class Model_item {
    public String videoId, title, uri;

    public Model_item() {
    }

    public Model_item(String videoId, String title, String uri) {
        this.videoId = videoId;
        this.title = title;
        this.uri = uri;
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
}
