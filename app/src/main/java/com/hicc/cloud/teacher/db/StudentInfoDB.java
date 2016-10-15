package com.hicc.cloud.teacher.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hicc.cloud.teacher.bean.Clas;
import com.hicc.cloud.teacher.bean.Division;
import com.hicc.cloud.teacher.bean.Family;
import com.hicc.cloud.teacher.bean.Grade;
import com.hicc.cloud.teacher.bean.Professional;
import com.hicc.cloud.teacher.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13/013.
 */

public class StudentInfoDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "hicc_student_info";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static StudentInfoDB studentInfoDB;
    private SQLiteDatabase db;

    // 私有化构造方法   单例模式
    private StudentInfoDB(Context context){
        StudentInfoOpenHelper dbHelper = new StudentInfoOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    // 获取 StudentInfoDB 实例
    public synchronized static StudentInfoDB getInstance(Context context){
        if(studentInfoDB == null){
            studentInfoDB = new StudentInfoDB(context);
        }
        return studentInfoDB;
    }

    /**
     * 年级
     * 将 Grade 实例存到数据库中
     */
    public void saveGrade(Grade grade){
        if(grade != null){
            ContentValues values = new ContentValues();
            values.put("grade_code", grade.getGradeCode());
            db.insert("Grade",null,values);
        }
    }

    /**
     * 获取所有年级
     * @return 年级集合
     */
    public List<Grade> getGrades(){
        List<Grade> list = new ArrayList<Grade>();
        Cursor cursor = db.query("Grade", null, null, null, null, null, null);
        while (cursor.moveToNext()){
            Grade grade = new Grade();
            grade.setId(cursor.getInt(cursor.getColumnIndex("id")));
            grade.setGradeCode(cursor.getInt(cursor.getColumnIndex("grade_code")));

            list.add(grade);
        }
        cursor.close();
        return list;
    }

    /**
     * 学部
     * 将 Division 实例存到数据库中
     */
    public void saveDivision(Division division){
        if(division != null){
            ContentValues values = new ContentValues();
            values.put("division_des", division.getDivisionDes());
            values.put("division_code", division.getDivisionCode());
            values.put("grade_code", division.getGradeCode());
            db.insert("Division",null,values);
        }
    }

    /**
     * 获取年级下的所有学部
     * @param gradeCode 年级代码
     * @return 学部集合
     */
    public List<Division> getDivisions(int gradeCode){
        List<Division> list = new ArrayList<Division>();
        Cursor cursor = db.query("Division", null, "grade_code = ?", new String[]{String.valueOf(gradeCode)}, null, null, null);
        while (cursor.moveToNext()){
            Division division = new Division();
            division.setId(cursor.getInt(cursor.getColumnIndex("id")));
            division.setDivisionDes(cursor.getString(cursor.getColumnIndex("division_des")));
            division.setDivisionCode(cursor.getInt(cursor.getColumnIndex("division_code")));
            division.setGradeCode(gradeCode);

            list.add(division);
        }
        cursor.close();
        return list;
    }

    /**
     * 专业
     * 将 Professional 实例存到数据库中
     */
    public void saveProfessional(Professional professional){
        if(professional != null){
            ContentValues values = new ContentValues();
            values.put("professional_des", professional.getProfessionalDes());
            values.put("professional_code", professional.getProfessionalCode());
            values.put("division_code", professional.getDivisionCode());
            db.insert("Professional",null,values);
        }
    }

    /**
     * 获取学部下的所有专业
     * @param divisionCode 学部代码
     * @return 专业集合
     */
    public List<Professional> getProfessionals(int divisionCode){
        List<Professional> list = new ArrayList<Professional>();
        Cursor cursor = db.query("Professional", null, "division_code = ?", new String[]{String.valueOf(divisionCode)}, null, null, null);
        while (cursor.moveToNext()){
            Professional professional = new Professional();
            professional.setId(cursor.getInt(cursor.getColumnIndex("id")));
            professional.setProfessionalDes(cursor.getString(cursor.getColumnIndex("professional_des")));
            professional.setProfessionalCode(cursor.getInt(cursor.getColumnIndex("professional_code")));
            professional.setDivisionCode(cursor.getInt(cursor.getColumnIndex("division_code")));

            list.add(professional);
        }
        cursor.close();
        return list;
    }

    /**
     * 班级
     * 将 Clas 实例存到数据库中
     */
    public void saveClass(Clas clas){
        if(clas != null){
            ContentValues values = new ContentValues();
            values.put("class_des", clas.getClassDes());
            values.put("class_code", clas.getClassCode());
            values.put("professional_code", clas.getProfessionalCode());
            values.put("grade_code", clas.getGradeCode());
            db.insert("Clas",null,values);
        }
    }

    /**
     * 获取专业下的所有班级
     * @param professionalCode 专业代码
     * @return 班级集合
     */
    public List<Clas> getClass(int professionalCode){
        List<Clas> list = new ArrayList<Clas>();
        Cursor cursor = db.query("Clas", null, "professional_code = ?", new String[]{String.valueOf(professionalCode)}, null, null, null);
        while (cursor.moveToNext()){
            Clas clas = new Clas();
            clas.setId(cursor.getInt(cursor.getColumnIndex("id")));
            clas.setClassDes(cursor.getString(cursor.getColumnIndex("class_des")));
            clas.setClassCode(cursor.getInt(cursor.getColumnIndex("class_code")));
            clas.setGradeCode(cursor.getInt(cursor.getColumnIndex("grade_code")));
            clas.setProfessionalCode(cursor.getInt(cursor.getColumnIndex("professional_code")));

            list.add(clas);
        }
        cursor.close();
        return list;
    }

    /**
     * 获取指定班级的 id
     * @param calssDes 班级描述
     * @param gradeCode 年级代码
     * @return int 类型 id
     */
    public int getClasIdForDB(String calssDes, int gradeCode){
        Cursor cursor = db.query("Clas", new String[]{"id","grade_code"}, "class_des = ?", new String[]{calssDes}, null, null, null);
        int id = -1;
        while (cursor.moveToNext()){
            if(cursor.getInt(cursor.getColumnIndex("grade_code")) == gradeCode){
                id = cursor.getInt(cursor.getColumnIndex("id"));
            }
        }
        return id;
    }

    /**
     * 学生
     * 将 Student 实例存到数据库中
     */
    public void saveStudent(Student student){
        if(student != null){
            ContentValues values = new ContentValues();
            values.put("class_code", student.getClassId());
            values.put("student_name", student.getStudentName());
            values.put("student_nu", student.getStudentNu());
            values.put("professional_des", student.getProfessionalDescription());
            values.put("gender_des", student.getGenderDescription());
            values.put("class_des", student.getClassDescription());
            values.put("payment_staus", student.getPaymentStausDescription());
            values.put("national_des", student.getNationalDescription());
            values.put("province_des", student.getProvinceDescription());
            values.put("dormitory_des", student.getDormitoryDescription());
            values.put("dormitory_no", student.getDormitoryNo());
            values.put("division_des", student.getDivisionDescription());
            values.put("weight", student.getWeight());
            values.put("phone", student.getYourPhone());
            values.put("grade_des", student.getGradeDescription());
            values.put("grade_code", student.getGradeCode());
            values.put("bed_number", student.getBedNumber());
            values.put("height", student.getHeight());
            values.put("old_school", student.getOldSchool());
            values.put("birth_date", student.getBirthDate());
            values.put("id_number", student.getIdNumber());
            values.put("enrollment_date", student.getEnrollmentDate());
            values.put("home_address", student.getHomeAddress());
            values.put("politics_status", student.getPoliticsStatusDescription());
            values.put("native_place", student.getNativePlace());
            values.put("live_report", student.getLiveReportStatueDescription());
            values.put("online_report", student.getOnlineReportStatueDescription());

            db.insert("Student",null,values);
        }
    }

    /**
     * 获取班级下所有学生
     * @param classId 班级id
     * @return 学生集合
     */
    public List<Student> getStudents(int classId){
        List<Student> list = new ArrayList<Student>();
        Cursor cursor = db.query("Student", null, "class_code = ?", new String[]{String.valueOf(classId)}, null, null, null);
        while (cursor.moveToNext()){
            Student student = new Student();
            student.setId(cursor.getInt(cursor.getColumnIndex("id")));
            student.setClassId(cursor.getInt(cursor.getColumnIndex("class_code")));
            student.setStudentName(cursor.getString(cursor.getColumnIndex("student_name")));
            student.setStudentNu(cursor.getString(cursor.getColumnIndex("student_nu")));
            student.setProfessionalDescription(cursor.getString(cursor.getColumnIndex("professional_des")));
            student.setGenderDescription(cursor.getString(cursor.getColumnIndex("gender_des")));
            student.setClassDescription(cursor.getString(cursor.getColumnIndex("class_des")));
            student.setPaymentStausDescription(cursor.getString(cursor.getColumnIndex("payment_staus")));
            student.setNationalDescription(cursor.getString(cursor.getColumnIndex("national_des")));
            student.setProvinceDescription(cursor.getString(cursor.getColumnIndex("province_des")));
            student.setDormitoryDescription(cursor.getString(cursor.getColumnIndex("dormitory_des")));
            student.setDormitoryNo(cursor.getInt(cursor.getColumnIndex("dormitory_no")));
            student.setDivisionDescription(cursor.getString(cursor.getColumnIndex("division_des")));
            student.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
            student.setYourPhone(cursor.getString(cursor.getColumnIndex("phone")));
            student.setGradeDescription(cursor.getString(cursor.getColumnIndex("grade_des")));
            student.setGradeCode(cursor.getInt(cursor.getColumnIndex("grade_code")));
            student.setExamineeTypeDescription(cursor.getString(cursor.getColumnIndex("examinee_type")));
            student.setBedNumber(cursor.getString(cursor.getColumnIndex("bed_number")));
            student.setFormerName(cursor.getString(cursor.getColumnIndex("former_name")));
            student.setHeight(cursor.getString(cursor.getColumnIndex("height")));
            student.setOldSchool(cursor.getString(cursor.getColumnIndex("old_school")));
            student.setBirthDate(cursor.getString(cursor.getColumnIndex("birth_date")));
            student.setForeignLanguagesDescription(cursor.getString(cursor.getColumnIndex("foreign_languages")));
            student.setIdNumber(cursor.getString(cursor.getColumnIndex("id_number")));
            student.setEnrollmentDate(cursor.getString(cursor.getColumnIndex("enrollment_date")));
            student.setHomeAddress(cursor.getString(cursor.getColumnIndex("home_address")));
            student.setFixedTelephone(cursor.getString(cursor.getColumnIndex("fixed_telephone")));
            student.setPoliticsStatusDescription(cursor.getString(cursor.getColumnIndex("politics_status")));
            student.setNativePlace(cursor.getString(cursor.getColumnIndex("native_place")));
            student.setLiveReportStatueDescription(cursor.getString(cursor.getColumnIndex("live_report")));
            student.setOnlineReportStatueDescription(cursor.getString(cursor.getColumnIndex("online_report")));

            list.add(student);
        }
        cursor.close();
        return list;
    }

    /**
     * 家庭
     * 将 Family 实例存到数据库中
     */
    public void saveFamily(Family family){
        if(family != null){
            ContentValues values = new ContentValues();
            values.put("student_nu", family.getStudentNum());
            values.put("name", family.getName());
            values.put("workand", family.getWorkand());
            values.put("relation", family.getRelation());
            values.put("phone", family.getPhone());
            values.put("age", family.getAge());
            values.put("politics", family.getPolitics());
            values.put("contact_address", family.getContactAddress());

            db.insert("Family",null,values);
        }
    }

    /**
     * 获取学生的家庭信息
     * @param studentNum 学生学号
     * @return 家庭信息集合
     */
    public List<Family> getFamilys(String studentNum){
        List<Family> list = new ArrayList<Family>();
        Cursor cursor = db.query("Family", null, "student_nu = ?", new String[]{studentNum}, null, null, null);
        while (cursor.moveToNext()){
            Family family = new Family();
            family.setId(cursor.getInt(cursor.getColumnIndex("id")));
            family.setStudentNum(cursor.getString(cursor.getColumnIndex("student_nu")));
            family.setName(cursor.getString(cursor.getColumnIndex("name")));
            family.setWorkand(cursor.getString(cursor.getColumnIndex("workand")));
            family.setRelation(cursor.getString(cursor.getColumnIndex("relation")));
            family.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            family.setAge(cursor.getInt(cursor.getColumnIndex("age")));
            family.setPolitics(cursor.getString(cursor.getColumnIndex("politics")));
            family.setContactAddress(cursor.getString(cursor.getColumnIndex("contact_address")));

            list.add(family);
        }
        cursor.close();
        return list;
    }

    /**
     * 获取对应学号的学生对象
     * @param stuNum 学号
     * @return Student 对象
     */
    public Student getStudent(String stuNum) {
        Student student = null;
        Cursor cursor = db.query("Student", null, "student_nu = ?", new String[]{stuNum}, null, null, null);
        while (cursor.moveToNext()){
            student = new Student();
            student.setId(cursor.getInt(cursor.getColumnIndex("id")));
            student.setClassId(cursor.getInt(cursor.getColumnIndex("class_code")));
            student.setStudentName(cursor.getString(cursor.getColumnIndex("student_name")));
            student.setStudentNu(cursor.getString(cursor.getColumnIndex("student_nu")));
            student.setProfessionalDescription(cursor.getString(cursor.getColumnIndex("professional_des")));
            student.setGenderDescription(cursor.getString(cursor.getColumnIndex("gender_des")));
            student.setClassDescription(cursor.getString(cursor.getColumnIndex("class_des")));
            student.setPaymentStausDescription(cursor.getString(cursor.getColumnIndex("payment_staus")));
            student.setNationalDescription(cursor.getString(cursor.getColumnIndex("national_des")));
            student.setProvinceDescription(cursor.getString(cursor.getColumnIndex("province_des")));
            student.setDormitoryDescription(cursor.getString(cursor.getColumnIndex("dormitory_des")));
            student.setDormitoryNo(cursor.getInt(cursor.getColumnIndex("dormitory_no")));
            student.setDivisionDescription(cursor.getString(cursor.getColumnIndex("division_des")));
            student.setWeight(cursor.getString(cursor.getColumnIndex("weight")));
            student.setYourPhone(cursor.getString(cursor.getColumnIndex("phone")));
            student.setGradeDescription(cursor.getString(cursor.getColumnIndex("grade_des")));
            student.setGradeCode(cursor.getInt(cursor.getColumnIndex("grade_code")));
            student.setBedNumber(cursor.getString(cursor.getColumnIndex("bed_number")));
            student.setHeight(cursor.getString(cursor.getColumnIndex("height")));
            student.setOldSchool(cursor.getString(cursor.getColumnIndex("old_school")));
            student.setBirthDate(cursor.getString(cursor.getColumnIndex("birth_date")));
            student.setIdNumber(cursor.getString(cursor.getColumnIndex("id_number")));
            student.setEnrollmentDate(cursor.getString(cursor.getColumnIndex("enrollment_date")));
            student.setHomeAddress(cursor.getString(cursor.getColumnIndex("home_address")));
            student.setPoliticsStatusDescription(cursor.getString(cursor.getColumnIndex("politics_status")));
            student.setNativePlace(cursor.getString(cursor.getColumnIndex("native_place")));
            student.setLiveReportStatueDescription(cursor.getString(cursor.getColumnIndex("live_report")));
            student.setOnlineReportStatueDescription(cursor.getString(cursor.getColumnIndex("online_report")));
        }
        cursor.close();
        return student;
    }
}
