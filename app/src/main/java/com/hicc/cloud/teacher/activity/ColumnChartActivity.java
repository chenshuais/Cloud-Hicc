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

import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/15/008.
 * 网上报到
 * 柱形图
 */
public class ColumnChartActivity extends AppCompatActivity {
    private View iv_back;
    private static final String URL = "http://home.hicc.cn/PhoneInterface/OnlineReportService.asmx/Getonlinereportnum";
    private int all;
    private int yes;
    private int no;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_column_chart);

        initUI();

        // 请求数据
        queryFromServer(savedInstanceState);
    }

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
                getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment(all,yes,no)).commit();
                closeProgressDialog();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private ColumnChartView chart;
        private ColumnChartData data;

        private boolean hasAxes = true;
        private boolean hasAxesNames = true;
        private boolean hasLabels = true;
        private boolean hasLabelForSelected = false;

        private int all;
        private int yes;
        private int no;

        @SuppressLint("ValidFragment")
        public PlaceholderFragment(int all, int yes, int no) {
            this.all = all;
            this.yes = yes;
            this.no = no;
        }

        public PlaceholderFragment(){}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            setHasOptionsMenu(true);
            View rootView = inflater.inflate(R.layout.fragment_column_chart, container, false);

            chart = (ColumnChartView) rootView.findViewById(R.id.chart);
            chart.setOnValueTouchListener(new ValueTouchListener());
            // 禁止缩放
            chart.setZoomEnabled(false);

            generateDefaultData();

            return rootView;
        }

        // 添加数据
        private void generateDefaultData() {
            List<Column> columns = new ArrayList<Column>();
            List<SubcolumnValue> values;

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(all, ChartUtils.pickColor()));
            Column column = new Column(values);
            column.setHasLabels(hasLabels);
            column.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column);

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(yes, ChartUtils.pickColor()));
            Column column2 = new Column(values);
            column2.setHasLabels(hasLabels);
            column2.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column2);

            values = new ArrayList<SubcolumnValue>();
            values.add(new SubcolumnValue(no, ChartUtils.pickColor()));
            Column column3 = new Column(values);
            column3.setHasLabels(hasLabels);
            column3.setHasLabelsOnlyForSelected(hasLabelForSelected);
            columns.add(column3);

            data = new ColumnChartData(columns);

            // 坐标
            if (hasAxes) {
                Axis axisX = new Axis();
                Axis axisY = new Axis().setHasLines(true);
                if (hasAxesNames) {
                    axisX.setName("状态");
                    axisY.setName("人数");
                }
                data.setAxisXBottom(axisX);
                data.setAxisYLeft(axisY);
            } else {
                data.setAxisXBottom(null);
                data.setAxisYLeft(null);
            }

            chart.setColumnChartData(data);
        }

        // 触摸事件
        private class ValueTouchListener implements ColumnChartOnValueSelectListener {
            @Override
            public void onValueSelected(int columnIndex, int subcolumnIndex, SubcolumnValue value) {
                // 获取点击的条目数值
                String vaule = value.toString();
                int end = vaule.length()-3;
                // 截取所需字符串
                String subV = vaule.substring(19,end);
                switch (columnIndex){
                    case 0:
                        ToastUtli.show(getContext(),"总共"+subV+"人");
                        break;
                    case 1:
                        ToastUtli.show(getContext(),"已报到"+subV+"人");
                        break;
                    case 2:
                        ToastUtli.show(getContext(),"未报到"+subV+"人");
                        break;
                }
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
