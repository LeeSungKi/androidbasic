package com.example.ex03_listview;

public class ItemVo {

    private String title;
    private String content;
    private int image;

    public  ItemVo(String title,String content){
        this(title,content,R.mipmap.ic_launcher);
    }
    public ItemVo(String title, String content, int image) {
        this.title = title;
        this.content = content;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {return title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
