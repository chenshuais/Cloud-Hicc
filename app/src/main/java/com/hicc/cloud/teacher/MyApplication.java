package com.hicc.cloud.teacher;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Bmob
        Bmob.initialize(this, "8266a5af2719a5062d139b829922d2d4");
    }
}
