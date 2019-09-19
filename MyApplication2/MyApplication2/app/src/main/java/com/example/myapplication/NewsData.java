package com.example.myapplication;

import java.io.Serializable;
//데이터분리용도
public class NewsData implements Serializable {
    private  String title;
    private  String urlToImage;
    private  String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
