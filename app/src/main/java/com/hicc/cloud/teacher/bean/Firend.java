package com.hicc.cloud.teacher.bean;


public class Firend {
    private String name;
    private String phone;
    private String time;
    private int imageId;

    public Firend(String name, String phone, String time, int imageId) {
        this.name = name;
        this.phone = phone;
        this.time = time;
        this.imageId = imageId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

