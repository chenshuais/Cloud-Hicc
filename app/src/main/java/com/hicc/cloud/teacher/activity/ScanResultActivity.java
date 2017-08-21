package com.hicc.cloud.teacher.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hicc.cloud.R;
import com.hicc.cloud.teacher.utils.Logs;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/15/015.
 */
public class ScanResultActivity extends AppCompatActivity {

    private ImageView iv_back;
    private ImageView iv_old_pic;
    private ImageView iv_new_pic;
    private TextView tv_num;
    private TextView tv_name;
    private TextView tv_faculty;
    private TextView tv_profession;
    private TextView tv_class;
    private TextView tv_dormitory;
    private TextView tv_pay_status;
    private TextView tv_reported_status;
    private Button bt_reported;
    private String result;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        initUI();

        Intent intent = getIntent();
        result = intent.getStringExtra("result");
        Logs.i(result);

        initData();
    }

    private void initData() {
        showDialog();
        OkHttpUtils
                .get()
                .url("http://api.hicc.cn/ll")
                .addParams("","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtli.show(getApplicationContext(),"获取学生信息失败，请稍后重试");
                        // 隐藏按钮
                        bt_reported.setVisibility(View.INVISIBLE);
                        closeDialog();
                        Logs.e("获取学生信息失败:"+e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("ssss")) {
                                JSONObject data = jsonObject.getJSONObject("data");
                                String num = data.getString("name");
                                String name = data.getString("num");
                                String faculty = data.getString("de");
                                String profession = data.getString("de");
                                String classs = data.getString("de");
                                String dormitory = data.getString("de");
                                String oldImg = data.getString("de");
                                String newImg = data.getString("de");
                                int payStatusCode = data.getInt("de");
                                int reportedStatus = data.getInt("de");

                                String oldImgUrl = "http://home.hicc.cn/OldImage/" + oldImg;
                                String newImgUrl = "http://home.hicc.cn/StudentImage/" + newImg;

                                Glide.with(ScanResultActivity.this).load(oldImgUrl).placeholder(R.drawable.icon_pic)
                                        .centerCrop()
                                        .error(R.drawable.icon_pic)
                                        .into(iv_old_pic);
                                Glide.with(ScanResultActivity.this).load(newImgUrl).placeholder(R.drawable.icon_pic)
                                        .centerCrop()
                                        .error(R.drawable.icon_pic)
                                        .into(iv_new_pic);
                                tv_num.setText("学号："+num);
                                tv_name.setText("姓名："+name);
                                tv_faculty.setText("学部："+faculty);
                                tv_profession.setText("专业："+profession);
                                tv_class.setText("班级："+classs);
                                tv_dormitory.setText("宿舍信息："+dormitory);

                                switch (payStatusCode) {
                                    case 0:
                                        tv_pay_status.setText("交费状态：已交费");
                                        break;
                                    case 1:
                                        tv_pay_status.setText("交费状态：未交费");
                                        // 隐藏按钮
                                        bt_reported.setVisibility(View.INVISIBLE);
                                        break;
                                    case 2:
                                        tv_pay_status.setText("交费状态：缓交");
                                        break;
                                }
                                switch (reportedStatus) {
                                    case 0:
                                        tv_reported_status.setText("报道状态：已报道");
                                        // 隐藏按钮
                                        bt_reported.setVisibility(View.INVISIBLE);
                                        break;
                                    case 1:
                                        tv_reported_status.setText("报道状态：未报道");
                                        break;
                                }
                            } else {
                                closeDialog();
                                ToastUtli.show(getApplicationContext(),"获取学生信息失败："+jsonObject.getString("Msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            closeDialog();
                            ToastUtli.show(getApplicationContext(),"获取学生信息失败，请稍后重试");
                            Logs.e("解析数据失败:"+e.toString());
                        }
                    }
                });
    }

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_old_pic = (ImageView) findViewById(R.id.iv_old_pic);
        iv_new_pic = (ImageView) findViewById(R.id.iv_new_pic);
        tv_num = (TextView) findViewById(R.id.tv_num);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_faculty = (TextView) findViewById(R.id.tv_faculty);
        tv_profession = (TextView) findViewById(R.id.tv_profession);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_dormitory = (TextView) findViewById(R.id.tv_dormitory);
        tv_pay_status = (TextView) findViewById(R.id.tv_pay_status);
        tv_reported_status = (TextView) findViewById(R.id.tv_reported_status);

        bt_reported = (Button) findViewById(R.id.bt_reported);
        // 报道按钮点击事件
        bt_reported.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeReportStatus();
            }
        });
    }

    private void changeReportStatus() {
        showDialog();
        OkHttpUtils
                .get()
                .url("http://api.hicc.cn/dd")
                .addParams("","")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        closeDialog();
                        ToastUtli.show(getApplicationContext(),"修改报道状态失败，请稍后重试");
                        Logs.e("修改报道状态失败:"+e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("dsd")) {
                                closeDialog();
                                ToastUtli.show(getApplicationContext(),"修改报道状态成功");
                                finish();
                            } else {
                                closeDialog();
                                ToastUtli.show(getApplicationContext(),"修改报道失败："+jsonObject.getString("Msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            closeDialog();
                            ToastUtli.show(getApplicationContext(),"修改报道状态失败，请稍后重试");
                            Logs.e("解析异常:"+e.toString());
                        }
                    }
                });
    }

    private void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("加载中...");
        progressDialog.show();
    }

    private void closeDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
