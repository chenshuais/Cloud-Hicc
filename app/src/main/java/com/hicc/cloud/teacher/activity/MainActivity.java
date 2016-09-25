package com.hicc.cloud.teacher.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.db.UpdateFile;
import com.hicc.cloud.teacher.fragment.BaseFragment;
import com.hicc.cloud.teacher.fragment.FriendFragment;
import com.hicc.cloud.teacher.fragment.HomeFragment;
import com.hicc.cloud.teacher.fragment.InformationFragment;
import com.hicc.cloud.teacher.utils.ConstantValue;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.hicc.cloud.teacher.view.MyTabLayout;
import com.hicc.cloud.teacher.view.ScrollViewPager;
import com.hicc.cloud.teacher.view.TabItem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;


public class MainActivity extends AppCompatActivity implements MyTabLayout.OnTabClickListener{
    private MyTabLayout mTabLayout;
    BaseFragment fragment;
    ScrollViewPager mViewPager;
    ArrayList<TabItem>tabs;
    private ProgressDialog progressDialog;
    private BmobQuery<UpdateFile> bmobQuery = new BmobQuery<UpdateFile>();
    private File file = new File(ConstantValue.downloadpathName);
    private BmobFile mBmobfile;
    private static Boolean isExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

        // 检测更新
        checkVersionCode();

    }

    private void initView(){
        mTabLayout=(MyTabLayout)findViewById(R.id.tablayout);
        mViewPager=(ScrollViewPager)findViewById(R.id.viewpager);
        // 设置viewpager是否禁止滑动
        mViewPager.setNoScroll(true);
    }

    private void initData(){
        tabs=new ArrayList<TabItem>();
        tabs.add(new TabItem(R.drawable.selector_tab_home, R.string.tab_home, HomeFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_friend, R.string.tab_friend, FriendFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_infomation, R.string.tab_information, InformationFragment.class));

        mTabLayout.initData(tabs, this);
        mTabLayout.setCurrentTab(0);

        FragAdapter adapter = new FragAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // tab的点击事件
    @Override
    public void onTabClick(TabItem tabItem) {
        mViewPager.setCurrentItem(tabs.indexOf(tabItem));
    }

    // Fragment适配器
    public class FragAdapter extends FragmentPagerAdapter {
        public FragAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            try {
                return tabs.get(arg0).tagFragmentClz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

    }

    // 检测更新
    private void checkVersionCode() {
        if(bmobQuery != null){
            bmobQuery.findObjects(new FindListener<UpdateFile>() {
                @Override
                public void done(List<UpdateFile> object, BmobException e) {
                    if(e==null){
                        for (UpdateFile updatefile : object) {
                            // 如果服务器的版本号大于本地的  就更新
                            if(updatefile.getVersion() > getVersionCode()){
                                BmobFile bmobfile = updatefile.getFile();
                                mBmobfile = bmobfile;
                                // 文件路径不为null  并且sd卡可用
                                if(file != null && Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                                    // 展示下载对话框
                                    showUpDataDialog(updatefile.getDescription(),bmobfile,file);
                                }
                            }
                        }
                    }else{
                        Log.i("Bmob文件传输","查询失败："+e.getMessage());
                    }
                }
            });
        }
    }

    // 显示更新对话框
    protected void showUpDataDialog(String description, final BmobFile bmobfile, final File file) {
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        //设置对话框左上角图标
        builder.setIcon(R.mipmap.ic_launcher);
        //设置对话框标题
        builder.setTitle("发现新版本");
        //设置对话框内容
        builder.setMessage(description);
        //设置积极的按钮
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //请求权限
                    requestCameraPermission();
                } else {
                    //下载apk
                    downLoadApk(bmobfile, file);
                    // 显示一个进度条对话框
                    showProgressDialog();
                }
            }
        });
        /*//设置消极的按钮
        builder.setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/
        //监听取消按钮  强制更新
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            //当点击返回的按钮时执行
            @Override
            public void onCancel(DialogInterface dialog) {
                //TODO 退出应用
                finish();
                System.exit(0);
            }
        });

        builder.show();
    }

    // 下载的进度条对话框
    protected void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("下载安装包中");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    // 下载文件
    private void downLoadApk(BmobFile bmobfile, final File file) {
        //调用bmobfile.download方法
        bmobfile.download(file, new DownloadFileListener() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    ToastUtli.show(getApplicationContext(),"下载成功,保存路径:"+ ConstantValue.downloadpathName);
                    Log.i("Bmob文件下载","下载成功,保存路径:"+ConstantValue.downloadpathName);
                    installApk(file);
                    progressDialog.dismiss();
                }else{
                    ToastUtli.show(getApplicationContext(),"下载失败："+e.getErrorCode()+","+e.getMessage());
                    Log.i("Bmob文件下载","下载失败："+e.getErrorCode()+","+e.getMessage());
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onProgress(Integer integer, long l) {
                progressDialog.setProgress(integer);
            }
        });
    }

    // 安装应用
    protected void installApk(File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        //文件作为数据源
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    // 获取本应用版本号
    private int getVersionCode() {
        // 拿到包管理者
        PackageManager pm = getPackageManager();
        // 获取包的基本信息
        try {
            PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
            // 返回应用的版本号
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 请求权限
    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    // 请求权限结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //下载apk
                downLoadApk(mBmobfile, file);
                // 显示一个进度条对话框
                showProgressDialog();
            } else {
                ToastUtli.show(getApplicationContext(),"请求写入文件");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    // 监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exitBy2Click();
        }
        return false;
    }

    // 双击退出程序
    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}
