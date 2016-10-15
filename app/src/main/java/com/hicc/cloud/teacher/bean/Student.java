package com.hicc.cloud.teacher.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/13/013.
 * 学生表
 */

public class Student implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // 学费情况
    private String PaymentStausDescription;
    // 党派
    private String PartyDuty;
    // 民族
    private String NationalDescription;
    // 学院ID
    private int CollegelId;
    private int Nid;
    // 爱好
    private String Habit;
    // 宿舍号
    private int DormitoryNo;
    // 姓名
    private String StudentName;
    // 国家代码
    private int NationalCode;
    // 性别代码
    private int GenderCode;
    // 学部代码
    private int DivisionCode;
    // 学部描述
    private String DivisionDescription;
    // 体重
    private String Weight;
    // 考生类型代码
    private int ExamineeTypeCode;
    // 政治状态代码
    private int PoliticsStatusCode;
    // 网上报道状态
    private int OnlineReportsStatusCode;
    // 电话
    private String YourPhone;
    // 邮政编码
    private String Postalcode;
    // 年级
    private String GradeDescription;
    // 考生类型说明
    private String ExamineeTypeDescription;
    // 外语语种
    private int ForeignLanguagesCode;
    // 是否华侨
    private int OverseasChineseWhetherCode;
    // 床号
    private String BedNumber;
    // 付款居留制
    private int PaymentStausCode;
    // 别名
    private String FormerName;
    // 身高
    private String Height;
    // 毕业学校
    private String OldSchool;
    // 学号
    private String StudentNu;
    // 生日
    private String BirthDate;
    // 高考准考证号
    private String CandidateNumber;
    // 外语语种
    private String ForeignLanguagesDescription;
    // 宿舍楼
    private String DormitoryDescription;
    private int LiveStatusReportCode;
    private String BarCode;
    private int BirthPlaceCode;
    // 专业
    private String ProfessionalDescription;
    // 身份证号
    private String IdNumber;
    // 省份
    private String ProvinceDescription;
    // 家长电话
    private String ParentsPhone;
    private String BloodTypeDescription;
    // 性别
    private String GenderDescription;
    private String ClassUserNo;
    private int Grade;
    private int GradeCode;
    // 入学时间
    private String EnrollmentDate;
    private String ExamNo;
    // 班级ID
    private int ClassId;
    private int ClassCode;

    public int getClassCode() {
        return ClassCode;
    }

    public void setClassCode(int classCode) {
        ClassCode = classCode;
    }

    private int BloodTypeCode;
    // 家庭住址
    private String HomeAddress;
    // 座机电话
    private String FixedTelephone;
    private int ProfessionId;
    // 现场报道状态
    private String LiveReportStatueDescription;
    // 政治面貌
    private String PoliticsStatusDescription;
    private String Character;
    // 班级
    private String ClassDescription;
    // 籍贯
    private String NativePlace;
    // 偶像
    private String Idol;
    // 网上报道状态
    private String OnlineReportStatueDescription;


    public String getPaymentStausDescription() {
        return PaymentStausDescription;
    }

    public void setPaymentStausDescription(String PaymentStausDescription) {
        this.PaymentStausDescription = PaymentStausDescription;
    }

    public String getPartyDuty() {
        return PartyDuty;
    }

    public void setPartyDuty(String PartyDuty) {
        this.PartyDuty = PartyDuty;
    }

    public String getNationalDescription() {
        return NationalDescription;
    }

    public void setNationalDescription(String NationalDescription) {
        this.NationalDescription = NationalDescription;
    }

    public int getCollegelId() {
        return CollegelId;
    }

    public void setCollegelId(int CollegelId) {
        this.CollegelId = CollegelId;
    }

    public int getNid() {
        return Nid;
    }

    public void setNid(int Nid) {
        this.Nid = Nid;
    }

    public String getHabit() {
        return Habit;
    }

    public void setHabit(String Habit) {
        this.Habit = Habit;
    }

    public int getDormitoryNo() {
        return DormitoryNo;
    }

    public void setDormitoryNo(int DormitoryNo) {
        this.DormitoryNo = DormitoryNo;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public int getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(int NationalCode) {
        this.NationalCode = NationalCode;
    }

    public int getGenderCode() {
        return GenderCode;
    }

    public void setGenderCode(int GenderCode) {
        this.GenderCode = GenderCode;
    }

    public int getDivisionCode() {
        return DivisionCode;
    }

    public void setDivisionCode(int DivisionCode) {
        this.DivisionCode = DivisionCode;
    }

    public String getDivisionDescription() {
        return DivisionDescription;
    }

    public void setDivisionDescription(String DivisionDescription) {
        this.DivisionDescription = DivisionDescription;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public int getExamineeTypeCode() {
        return ExamineeTypeCode;
    }

    public void setExamineeTypeCode(int ExamineeTypeCode) {
        this.ExamineeTypeCode = ExamineeTypeCode;
    }

    public int getPoliticsStatusCode() {
        return PoliticsStatusCode;
    }

    public void setPoliticsStatusCode(int PoliticsStatusCode) {
        this.PoliticsStatusCode = PoliticsStatusCode;
    }

    public int getOnlineReportsStatusCode() {
        return OnlineReportsStatusCode;
    }

    public void setOnlineReportsStatusCode(int OnlineReportsStatusCode) {
        this.OnlineReportsStatusCode = OnlineReportsStatusCode;
    }

    public String getYourPhone() {
        return YourPhone;
    }

    public void setYourPhone(String YourPhone) {
        this.YourPhone = YourPhone;
    }

    public String getPostalcode() {
        return Postalcode;
    }

    public void setPostalcode(String Postalcode) {
        this.Postalcode = Postalcode;
    }

    public String getGradeDescription() {
        return GradeDescription;
    }

    public void setGradeDescription(String GradeDescription) {
        this.GradeDescription = GradeDescription;
    }

    public String getExamineeTypeDescription() {
        return ExamineeTypeDescription;
    }

    public void setExamineeTypeDescription(String ExamineeTypeDescription) {
        this.ExamineeTypeDescription = ExamineeTypeDescription;
    }

    public int getForeignLanguagesCode() {
        return ForeignLanguagesCode;
    }

    public void setForeignLanguagesCode(int ForeignLanguagesCode) {
        this.ForeignLanguagesCode = ForeignLanguagesCode;
    }

    public int getOverseasChineseWhetherCode() {
        return OverseasChineseWhetherCode;
    }

    public void setOverseasChineseWhetherCode(int OverseasChineseWhetherCode) {
        this.OverseasChineseWhetherCode = OverseasChineseWhetherCode;
    }

    public String getBedNumber() {
        return BedNumber;
    }

    public void setBedNumber(String BedNumber) {
        this.BedNumber = BedNumber;
    }

    public int getPaymentStausCode() {
        return PaymentStausCode;
    }

    public void setPaymentStausCode(int PaymentStausCode) {
        this.PaymentStausCode = PaymentStausCode;
    }

    public String getFormerName() {
        return FormerName;
    }

    public void setFormerName(String FormerName) {
        this.FormerName = FormerName;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String Height) {
        this.Height = Height;
    }

    public String getOldSchool() {
        return OldSchool;
    }

    public void setOldSchool(String OldSchool) {
        this.OldSchool = OldSchool;
    }

    public String getStudentNu() {
        return StudentNu;
    }

    public void setStudentNu(String StudentNu) {
        this.StudentNu = StudentNu;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String BirthDate) {
        this.BirthDate = BirthDate;
    }

    public String getCandidateNumber() {
        return CandidateNumber;
    }

    public void setCandidateNumber(String CandidateNumber) {
        this.CandidateNumber = CandidateNumber;
    }

    public String getForeignLanguagesDescription() {
        return ForeignLanguagesDescription;
    }

    public void setForeignLanguagesDescription(String ForeignLanguagesDescription) {
        this.ForeignLanguagesDescription = ForeignLanguagesDescription;
    }

    public String getDormitoryDescription() {
        return DormitoryDescription;
    }

    public void setDormitoryDescription(String DormitoryDescription) {
        this.DormitoryDescription = DormitoryDescription;
    }

    public int getLiveStatusReportCode() {
        return LiveStatusReportCode;
    }

    public void setLiveStatusReportCode(int LiveStatusReportCode) {
        this.LiveStatusReportCode = LiveStatusReportCode;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String BarCode) {
        this.BarCode = BarCode;
    }

    public int getBirthPlaceCode() {
        return BirthPlaceCode;
    }

    public void setBirthPlaceCode(int BirthPlaceCode) {
        this.BirthPlaceCode = BirthPlaceCode;
    }

    public String getProfessionalDescription() {
        return ProfessionalDescription;
    }

    public void setProfessionalDescription(String ProfessionalDescription) {
        this.ProfessionalDescription = ProfessionalDescription;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }

    public String getProvinceDescription() {
        return ProvinceDescription;
    }

    public void setProvinceDescription(String ProvinceDescription) {
        this.ProvinceDescription = ProvinceDescription;
    }

    public String getParentsPhone() {
        return ParentsPhone;
    }

    public void setParentsPhone(String ParentsPhone) {
        this.ParentsPhone = ParentsPhone;
    }

    public String getBloodTypeDescription() {
        return BloodTypeDescription;
    }

    public void setBloodTypeDescription(String BloodTypeDescription) {
        this.BloodTypeDescription = BloodTypeDescription;
    }

    public String getGenderDescription() {
        return GenderDescription;
    }

    public void setGenderDescription(String GenderDescription) {
        this.GenderDescription = GenderDescription;
    }

    public String getClassUserNo() {
        return ClassUserNo;
    }

    public void setClassUserNo(String ClassUserNo) {
        this.ClassUserNo = ClassUserNo;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int Grade) {
        this.Grade = Grade;
    }

    public int getGradeCode() {
        return GradeCode;
    }

    public void setGradeCode(int GradeCode) {
        this.GradeCode = GradeCode;
    }

    public String getEnrollmentDate() {
        return EnrollmentDate;
    }

    public void setEnrollmentDate(String EnrollmentDate) {
        this.EnrollmentDate = EnrollmentDate;
    }

    public String getExamNo() {
        return ExamNo;
    }

    public void setExamNo(String ExamNo) {
        this.ExamNo = ExamNo;
    }

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int ClassId) {
        this.ClassId = ClassId;
    }

    public int getBloodTypeCode() {
        return BloodTypeCode;
    }

    public void setBloodTypeCode(int BloodTypeCode) {
        this.BloodTypeCode = BloodTypeCode;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String HomeAddress) {
        this.HomeAddress = HomeAddress;
    }

    public String getFixedTelephone() {
        return FixedTelephone;
    }

    public void setFixedTelephone(String FixedTelephone) {
        this.FixedTelephone = FixedTelephone;
    }

    public int getProfessionId() {
        return ProfessionId;
    }

    public void setProfessionId(int ProfessionId) {
        this.ProfessionId = ProfessionId;
    }

    public String getLiveReportStatueDescription() {
        return LiveReportStatueDescription;
    }

    public void setLiveReportStatueDescription(String LiveReportStatueDescription) {
        this.LiveReportStatueDescription = LiveReportStatueDescription;
    }

    public String getPoliticsStatusDescription() {
        return PoliticsStatusDescription;
    }

    public void setPoliticsStatusDescription(String PoliticsStatusDescription) {
        this.PoliticsStatusDescription = PoliticsStatusDescription;
    }

    public String getCharacter() {
        return Character;
    }

    public void setCharacter(String Character) {
        this.Character = Character;
    }

    public String getClassDescription() {
        return ClassDescription;
    }

    public void setClassDescription(String ClassDescription) {
        this.ClassDescription = ClassDescription;
    }

    public String getNativePlace() {
        return NativePlace;
    }

    public void setNativePlace(String NativePlace) {
        this.NativePlace = NativePlace;
    }

    public String getIdol() {
        return Idol;
    }

    public void setIdol(String Idol) {
        this.Idol = Idol;
    }

    public String getOnlineReportStatueDescription() {
        return OnlineReportStatueDescription;
    }

    public void setOnlineReportStatueDescription(String OnlineReportStatueDescription) {
        this.OnlineReportStatueDescription = OnlineReportStatueDescription;
    }

}

