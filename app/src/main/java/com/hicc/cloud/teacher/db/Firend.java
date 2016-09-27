package com.hicc.cloud.teacher.db;


public class Firend {
    private String name;
    private String content;
    private String time;
    private int imageId;

    public Firend(String name, String content, String time, int imageId) {
        this.name = name;
        this.content = content;
        this.time = time;
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}

