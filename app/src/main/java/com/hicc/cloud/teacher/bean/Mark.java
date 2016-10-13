package com.hicc.cloud.teacher.bean;

/**
 * Created by 野 on 2016/10/13.
 */

public class Mark {
    private String name;
    private String[] course;
    private String[] mark;
    private String[] teacher;

    public Mark(String name,String[] course,String[] mark,String[] teacher){

        this.name=name;
        this.course=course;
        this.mark=mark;
        this.teacher=teacher;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getCourse() {
        return course;
    }

    public void setCourse(String[] course) {
        this.course = course;
    }

    public String[] getMark() {
        return mark;
    }

    public void setMark(String[] mark) {
        this.mark = mark;
    }

    public String[] getTeacher() {
        return teacher;
    }

    public void setTeacher(String[] teacher) {
        this.teacher = teacher;
    }
}
