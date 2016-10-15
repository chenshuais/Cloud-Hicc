package com.hicc.cloud.teacher.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.utils.Logs;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/15/008.
 * 现场报到
 * 饼状图
 */
public class PieChartActivity extends AppCompatActivity {

    private ImageView iv_back;
    private ProgressDialog progressDialog;
    private static final String URL = "http://home.hicc.cn/PhoneInterface/SceneReportService.asmx/Getscenereportnum";
    private int all;
    private int yes;
    private int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        initUI();

        // 请求数据
        queryFromServer(savedInstanceState);
    }

    // 从网络请求数据
    private void queryFromServer(final Bundle savedInstanceState) {
        showProgressDialog();
        // 发送GET请求
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("userno", "1001")
                .addParams("num", "20465")
                .addParams("userlevelcode", "11")
                .addParams("account", "hicc")
                .addParams("pas", "123")
                .addParams("timeCode", "16")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        closeProgressDialog();
                        Logs.i(e.toString());
                        ToastUtli.show(getApplicationContext(),"服务器繁忙，请重新查询");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Logs.i(response);
                        // 解析json
                        Logs.i("解析json");
                        getJsonInfo(response,savedInstanceState);
                    }
                });
    }

    // 解析json数据
    private void getJsonInfo(String response, Bundle savedInstanceState) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                all = jsonObject.getInt("All");
                yes = jsonObject.getInt("Yes");
                no = jsonObject.getInt("No");
            }
            if (savedInstanceState == null && all > 0) {
                getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment(yes,no)).commit();
                closeProgressDialog();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static class PlaceholderFragment extends Fragment {
        private PieChartView chart;
        private PieChartData data;
        private int yes;
        private int no;

        @SuppressLint("ValidFragment")
        public PlaceholderFragment(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }

        public PlaceholderFragment(){}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_pie_chart, container, false);

            chart = (PieChartView) rootView.findViewById(R.id.chart);
            chart.setOnValueTouchListener(new ValueTouchListener());

            generateData();

            return rootView;
        }

        // 设置数据
        private void generateData() {
            List<SliceValue> values = new ArrayList<SliceValue>();

            SliceValue sliceValue1 = new SliceValue(yes, ChartUtils.pickColor());
            sliceValue1.setLabel("已报到"+yes+"人");
            values.add(sliceValue1);

            SliceValue sliceValue2 = new SliceValue(no, ChartUtils.pickColor());
            sliceValue2.setLabel("未报到"+no+"人");
            values.add(sliceValue2);

            data = new PieChartData(values);
            data.setHasLabels(true);
            data.setHasLabelsOnlyForSelected(false);
            data.setHasLabelsOutside(false);
            chart.setValueSelectionEnabled(true);
            chart.setCircleFillRatio(1.0f);

            chart.setPieChartData(data);
        }

        private class ValueTouchListener implements PieChartOnValueSelectListener {
            @Override
            public void onValueSelected(int arcIndex, SliceValue value) {
                // 点击事件
            }

            @Override
            public void onValueDeselected() {
            }
        }
    }

    // 显示进度对话框
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
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

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
