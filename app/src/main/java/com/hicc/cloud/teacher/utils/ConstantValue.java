package com.hicc.cloud.teacher.utils;

import android.os.Environment;

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
     * 更新应用下载保存路径
     */
    public static String downloadpathName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/云上工商.apk";
}
