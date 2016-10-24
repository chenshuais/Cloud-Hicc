package com.hicc.cloud.teacher.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.utils.ConstantValue;
import com.hicc.cloud.teacher.utils.Logs;
import com.hicc.cloud.teacher.utils.MD5Util;
import com.hicc.cloud.teacher.utils.ProgressDialogUtil;
import com.hicc.cloud.teacher.utils.SpUtils;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/17/017.
 * 登录页面
 */

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String URL = "http://suguan.hicc.cn/hiccphonet/LoginT";
    private static Boolean isExit = false;
    private CheckBox cb_rember;
    private EditText et_username;
    private EditText et_pwd;
    private ImageView iv_image;
    private ProgressDialog progressDialog;
    private String userName;
    private String mPwd;
    private ProgressDialogUtil progressDialogUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_bak);

        progressDialogUtil = new ProgressDialogUtil(this,"登录中");

        // 初始化控件
        initUI();

        //检测是否记住了密码 如果是就填充
        checkUpDown();
        //检查是否已经登陆
        checkEnter();
    }

    // 点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 登录按钮
            case R.id.bt_login:
                // 登录
                loginButton();
                break;
            // 注册按钮
            case R.id.tv_register:
                ToastUtli.show(getApplicationContext(),"正在开发中");
                break;
            // 找回密码按钮
            case R.id.tv_find_pwd:
                ToastUtli.show(getApplicationContext(),"正在开发中");
                break;
        }
    }

    // 登录
    private void loginButton() {
        userName = et_username.getText().toString().trim();
        mPwd = et_pwd.getText().toString().trim();
        if (userName.equals("") || mPwd.equals("")) {
            ToastUtli.show(getApplicationContext(),"账号或密码不能为空");
        // TODO 接口不能用时的假数据
        } else if (userName.equals("123") && mPwd.equals("123")){
            progressDialogUtil.showProgressDialog();
            checkUp(userName,mPwd);
            //SpUtils.putStringSp(getApplicationContext());
            enterHome();
        } else {
            // 显示进度对话框
            showProgressDialog();
            // 对密码加密
            mPwd = MD5Util.str2md5(mPwd);
            // 向服务器请求数据 发送GET请求
            getFromServer(userName, mPwd);
            Logs.i("账号："+userName+"  密码："+mPwd);
        }
    }

    // 向服务器请求数据
    private void getFromServer(String userName, String mPwd) {
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("account", userName)
                .addParams("pass", mPwd)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        closeProgressDialog();
                        Logs.i(e.toString());
                        ToastUtli.show(getApplicationContext(),"服务器繁忙，请重新登录");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Logs.i(response);
                        // 解析json
                        Logs.i("解析json");
                        getJsonInfo(response);
                    }
                });
    }

    // 解析json数据
    private void getJsonInfo(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            boolean sucessed = jsonObject.getBoolean("sucessed");
            if(sucessed) {
                Logs.i("开始解析");
                // 登录成功后检查是否记住密码
                checkUp(userName,mPwd);
                Logs.i("账号："+userName+"  密码："+mPwd);
                // 关闭对话框进入主界面
                closeProgressDialog();
                enterHome();
                // TODO 保存用户信息
            }else{
                closeProgressDialog();
                ToastUtli.show(getApplicationContext(),"账号或密码错误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ToastUtli.show(getApplicationContext(),"登录失败");
        }

    }

    //检查是否已经登陆
    private void checkEnter() {
        if (SpUtils.getBoolSp(this, ConstantValue.IS_REMBER_PWD,false)) {
            // TODO 向服务器发送请求登录  获取用户数据
            enterHome();
        }
    }

    //检测是否记住了密码 如果是就填充
    private void checkUpDown() {
        if (SpUtils.getBoolSp(this, ConstantValue.IS_REMBER_PWD,false)) {
            et_username.setText(SpUtils.getStringSp(this,ConstantValue.USER_NAME,""));
            et_pwd.setText(SpUtils.getStringSp(this,ConstantValue.PASS_WORD,""));
            cb_rember.setChecked(SpUtils.getBoolSp(this, ConstantValue.IS_REMBER_PWD,false));
        }else {
            et_username.setText(SpUtils.getStringSp(this,ConstantValue.USER_NAME,""));
        }
    }

    //检查是否勾选记住密码
    private void checkUp(String userName,String mPwd) {
        if (cb_rember.isChecked()) {
            SpUtils.putStringSp(this,ConstantValue.USER_NAME,userName);
            SpUtils.putStringSp(this,ConstantValue.PASS_WORD,mPwd);
            SpUtils.putBoolSp(this,ConstantValue.IS_REMBER_PWD,true);
        } else {
            SpUtils.putStringSp(this,ConstantValue.USER_NAME,userName);
            SpUtils.putStringSp(this,ConstantValue.PASS_WORD,mPwd);
            SpUtils.putBoolSp(this,ConstantValue.IS_REMBER_PWD,false);
        }
    }

    //进入主界面
    private void enterHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    // 初始化控件
    private void initUI() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        cb_rember = (CheckBox) findViewById(R.id.cb_rember);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        Button bt_login = (Button) findViewById(R.id.bt_login);
        TextView tv_register = (TextView) findViewById(R.id.tv_register);
        TextView tv_find_pwd = (TextView) findViewById(R.id.tv_find_pwd);

        // 设置点击事件
        bt_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_find_pwd.setOnClickListener(this);
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            exitBy2Click();
        }
        return false;
    }

    //双击退出程序
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

    // 显示进度对话框
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("登陆中...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    return;
                }
            });
        }
        progressDialog.show();
    }

    // 关闭进度对话框
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
