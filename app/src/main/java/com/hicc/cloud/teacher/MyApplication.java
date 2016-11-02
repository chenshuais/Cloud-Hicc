package com.hicc.cloud.teacher;

import android.app.Application;
import android.os.Environment;

import com.hicc.cloud.teacher.bean.PhoneInfo;
import com.hicc.cloud.teacher.db.ExceptionFile;
import com.hicc.cloud.teacher.utils.Logs;
import com.hicc.cloud.teacher.utils.PhoneInfoUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;
import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

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

        // okhttp网络请求工具配置信息
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);


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
                ExceptionFile exceptionFile = new ExceptionFile();

                PhoneInfo phoneInfo = PhoneInfoUtil.getPhoneInfo(getApplicationContext());
                exceptionFile.setPhoneBrand(phoneInfo.getPhoneBrand());
                exceptionFile.setPhoneBrandType(phoneInfo.getPhoneBrandType());
                exceptionFile.setAndroidVersion(phoneInfo.getAndroidVersion());
                exceptionFile.setCpuName(phoneInfo.getCpuName());
                BmobFile bmobFile = new BmobFile(file);
                exceptionFile.setExceptionFile(bmobFile);
                exceptionFile.save(getApplicationContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Logs.i("错误日志上传成功");
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Logs.i("错误日志上传失败："+s);
                    }
                });

                // 手动退出应用
                System.exit(0);
            }
        });
    }
}
