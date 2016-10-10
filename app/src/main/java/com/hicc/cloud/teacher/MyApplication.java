package com.hicc.cloud.teacher;

import android.app.Application;
import android.os.Environment;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化Bmob
        Bmob.initialize(this, "8266a5af2719a5062d139b829922d2d4");

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

        ZXingLibrary.initDisplayOpinion(this);  //二维码扫描

        // 捕获全局未捕获的异常
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                // 输出异常
                ex.printStackTrace();

                // 将打印的异常存到SD卡中
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"cloud_hicc_error.log";
                File file = new File(path);
                try {
                    PrintWriter printWriter = new PrintWriter(file);
                    ex.printStackTrace(printWriter);
                    printWriter.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                // 可以将异常文件上传到服务器

                // 手动退出应用
                System.exit(0);
            }
        });
    }
}
