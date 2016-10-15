package com.hicc.cloud.teacher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.utils.Logs;

/**
 * Created by Administrator on 2016/10/15/015.
 */
public class ScanResultActivity extends AppCompatActivity {

    private ImageView iv_back;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_result);

        initUI();

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        Logs.i(result);
        tv_result.setText(result);
    }

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_result = (TextView) findViewById(R.id.tv_result);
    }
}
