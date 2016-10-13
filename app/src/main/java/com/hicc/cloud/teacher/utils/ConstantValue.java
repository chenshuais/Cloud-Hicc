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
     * 更新应用下载保存路径
     */
    public static String downloadpathName = Environment.getExternalStorageDirectory().getAbsolutePath()+"/云上工商.apk";
}
