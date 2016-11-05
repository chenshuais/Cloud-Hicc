package com.hicc.cloud.teacher.utils;

import android.os.Environment;

import com.hicc.cloud.teacher.activity.FeedBackActivity;

/**
 * Created on 2016/9/24/023.
 * 存储常量
 */
public class ConstantValue {
    /**
     * 是否首次加载数据
     */
    public static final String FIRST_DATA = "first_data";
    /**
     * 是否记住密码
     */
    public static final String IS_REMBER_PWD = "is_rember_pwd";
    /**
     * 保存的用户账号
     */
    public static final String USER_NAME = "user_name";
    /**
     * 保存的用户密码
     */
    public static final String PASS_WORD = "pass_word";
    /**
     * 老师姓名
     */
    public static final String TEACHER_NAME = "teacher_name";
    /**
     * 老师职位
     */
    public static final String TEACHER_LEVEL = "teacher_level";
    /**
     * 老师电话
     */
    public static final String TEACHER_PHONE = "teacher_phone";
    /**
     * UserNo
     */
    public static final String USER_NO = "user_no";
    /**
     * RecordCode
     */
    public static final String RECORD_CODE = "record_code";
    /**
     * Nid
     */
    public static final String NID = "nid";
    /**
     * UserLevelCode
     */
    public static final String USER_LEVEL_CODE = "user_level_code";
    /**
     * 是否是第一次上传手机信息
     */
    public static final String FIRST_UP_PHONE_INFO = "first_up_phone_info";
    /**
     * App_id
     */
    public static final String APP_ID = "1";
    /**
     * 版本号
     */
    public static final String VERSION_CODE = "date;";
    /**
     * 更新应用下载保存路径
     */
    public static String downloadpathName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/云上工商.apk";
}
