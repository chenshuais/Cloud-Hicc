package com.hicc.cloud.teacher.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.utils.ConstantValue;
import com.hicc.cloud.teacher.utils.Logs;
import com.hicc.cloud.teacher.utils.SpUtils;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/11/4/004.
 * 反馈   ——崔国钊
 */
public class FeedBackActivity extends AppCompatActivity {

    private ImageView iv_back;
    private Button bt_send;
    private static final String URL = "http://suguan.hicc.cn/feedback1/suggest.do";
    private EditText ed_sendtext;
    public String date=this.getVersionCode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        initUI();

    }

    public String getVersionCode()  {
        //获取包管理者
      PackageManager pm = getPackageManager();
       //从包管理者中获取基本信息
        PackageInfo packageInfo= null;
        try {
            packageInfo = pm.getPackageInfo(getPackageName(),0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void initUI() {
        ed_sendtext= (EditText) findViewById(R.id.sendtext);
        bt_send= (Button) findViewById(R.id.bt_send);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String massage=ed_sendtext.getText().toString().trim();
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils
                        .get()
                        .url(URL)
                        .addParams("userId",SpUtils.getStringSp(getApplicationContext(), ConstantValue.USER_NAME,""))
                        .addParams("appId",SpUtils.getStringSp(getApplicationContext(),ConstantValue.APP_ID,""))
                        .addParams("tag",SpUtils.getStringSp(getApplicationContext(),ConstantValue.USER_NAME,""))
                        .addParams("content",SpUtils.getStringSp(getApplicationContext(),massage,""))
                        //.addParams("appVersion",SpUtils.getStringSp(getApplicationContext(),ConstantValue.VERSION_CODE,"")
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Logs.i(e.toString());
                                ToastUtli.show(getApplicationContext(),"服务器繁忙，请重新发送");
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                ToastUtli.show(getApplicationContext(),"发送成功");
                            }
                        });

            }
        });

    }

}
