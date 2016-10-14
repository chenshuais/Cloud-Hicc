package com.hicc.cloud.teacher.bean;

/**
 * Created by Administrator on 2016/10/13/013.
 * 班级表
 */

public class Clas {
    private int id;
    private String classDes;
    private int classCode;
    private int gradeCode;
    private int professionalCode;

    public int getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(int gradeCode) {
        this.gradeCode = gradeCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassDes() {
        return classDes;
    }

    public void setClassDes(String classDes) {
        this.classDes = classDes;
    }

    public int getClassCode() {
        return classCode;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public int getProfessionalCode() {
        return professionalCode;
    }

    public void setProfessionalCode(int professionalCode) {
        this.professionalCode = professionalCode;
    }
}
