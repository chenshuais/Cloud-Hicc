package com.hicc.cloud.teacher.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.utils.ToastUtli;

/**
 * Created by Administrator on 2016/11/4/004.
 * 设置   ——崔国钊
 */
public class SettingActivity extends AppCompatActivity {

    private ImageView iv_back;
    private RelativeLayout rl_massage;
    private RelativeLayout rl_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUI();
    }

    private void initUI() {
        rl_massage= (RelativeLayout) findViewById(R.id.rl_massage);
        rl_password= (RelativeLayout) findViewById(R.id.rl_password);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rl_massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtli.show(getApplicationContext(),"努力开发中");
            }
        });
        rl_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtli.show(getApplicationContext(),"努力开发中");
            }
        });
    }
}
