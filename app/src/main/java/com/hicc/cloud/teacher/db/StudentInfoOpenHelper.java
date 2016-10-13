package com.hicc.cloud.teacher.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/13/013.
 */

public class StudentInfoOpenHelper extends SQLiteOpenHelper {
    /**
     * 创建年级表语句
     */
    public static final String CREAT_GRADE = "create table Grade (id integer primary key autoincrement, grade_code integer)";
    /**
     * 创建学部表语句
     */
    public static final String CREAT_DIVISION = "create table Division (id integer primary key autoincrement, division_des text, division_code integer, grade_code integer)";
    /**
     * 创建专业表语句
     */
    public static final String CREAT_PROFESSIONAL = "create table Professional (id integer primary key autoincrement, professional_des text, professional_code integer, division_code integer)";
    /**
     * 创建班级表语句
     */
    public static final String CREAT_CLASS = "create table Clas (id integer primary key autoincrement, class_des text, class_code integer, professional_code integer)";
    /**
     * 创建学生表语句
     */
    public static final String CREAT_STUDENT = "create table Student (" +
            // id   班级代码    学生姓名
            "id integer primary key autoincrement, class_code integer, student_name text, " +
            // 学号   专业  性别  班级
            "student_nu text, professional_des text, gender_des text, class_des text, " +
            // 缴费状态  民族     省份
            "payment_staus text, national_des text, province_des text" +
            // 宿舍   学部  体重  电话  年级  考生类型
            "dormitory_des text, division_des text, weight text, phone text, grade_des text, examinee_type text, " +
            // 床号   别名  身高  毕业学校    生日  外语语种
            "bed_number text, former_name text, height text, old_school text, birth_date text, foreign_languages text, " +
            // 身份证号     入学时间    家庭住址    座机电话    政治面貌
            "id_number text, enrollment_date text, home_address text, fixed_telephone text, politics_status text, " +
            // 籍贯   现场报道    网上报道
            "native_place text, live_report text, online_report text)";
    /**
     * 创建家庭表语句
     */
    public static final String CREAT_FAMILY = "create table Family (id integer primary key autoincrement, student_nu text, " +
            // 姓名   工作  关系  电话  年龄  政治面貌    联系地址
            "name text, workand text, relation text, phone text, age integer, politics text, contact_address text)";


    public StudentInfoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_GRADE);    //年级表
        db.execSQL(CREAT_DIVISION);     //学部表
        db.execSQL(CREAT_PROFESSIONAL);     //专业表
        db.execSQL(CREAT_CLASS);       //班级表
        db.execSQL(CREAT_STUDENT);      //学生表
        db.execSQL(CREAT_FAMILY);   //家庭表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
