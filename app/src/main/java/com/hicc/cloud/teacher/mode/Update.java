package com.hicc.cloud.teacher.mode;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.db.UpdateFile;
import com.hicc.cloud.teacher.utils.ConstantValue;

import java.io.File;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class Update {
    private Activity mThis;
    private ProgressDialog progressDialog;
    private File file = new File(ConstantValue.downloadpathName);
    private String mDescription = "";
    private BmobQuery<UpdateFile> bmobQuery = new BmobQuery<UpdateFile>();
    private BmobFile mBmobfile = null;
    private int version;

    public Update(Activity mthis){
        this.mThis = mthis;
    }

    /**
     * 检测更新
     */
    public void checkUpdate() {
        int serverVersion = checkVersionCode();
        int currentVersion = getVersionCode();
        if(serverVersion > currentVersion){
            // 文件路径不为null  并且sd卡可用
            if(file != null && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                // 服务器版本号大于本地  更新应用
                showUpDataDialog();
            }
        }
    }

    // 检测服务器版本号   需要获得版本号，版本更新描述，apk下载地址
    private int checkVersionCode(){
        version = 1;
        return version;
    }

    // 显示更新对话框
    private void showUpDataDialog(){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(mThis);
        //设置对话框左上角图标
        builder.setIcon(R.mipmap.ic_launcher);
        //设置对话框标题
        builder.setTitle("发现新版本");
        //设置对话框内容
        builder.setMessage(mDescription);
        //设置积极的按钮
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(mThis, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //请求权限
                    requestCameraPermission();
                } else {
                    //下载apk
                    downLoadApk();
                }
            }
        });
        /*//设置消极的按钮
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO 退出应用
                dialog.dismiss();
            }
        });*/
        //监听取消按钮
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            //当点击返回的按钮时执行
            @Override
            public void onCancel(DialogInterface dialog) {
                // TODO 退出应用
                dialog.dismiss();
            }
        });

        builder.show();
    }

    // 下载文件
    public void downLoadApk() {
        // 显示进度条
        showProgressDialog();
    }

    // 安装应用
    protected void installApk(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        //文件作为数据源
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        mThis.startActivity(intent);
    }

    // 获取本应用版本号
    private int getVersionCode() {
        // 拿到包管理者
        PackageManager pm = mThis.getPackageManager();
        // 获取包的基本信息
        try {
            PackageInfo info = pm.getPackageInfo(mThis.getPackageName(), 0);
            // 返回应用的版本号
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 请求权限
    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(mThis,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        } else {
            ActivityCompat.requestPermissions(mThis, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    // 下载的进度条对话框
    protected void showProgressDialog() {
        progressDialog = new ProgressDialog(mThis);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("下载安装包中");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }
}
