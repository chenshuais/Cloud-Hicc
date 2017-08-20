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
    private int all;
    private int yes;
    private int no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        initUI();

        // 请求数据
        queryFromServer();
    }

    // 从网络请求数据
    private void queryFromServer() {
        showProgressDialog();
        OkHttpUtils
                .get()
                .url("http://api.hicc.cn/a")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        closeProgressDialog();
                        Logs.e("获取现场报道数据失败："+e.toString());
                        getSupportFragmentManager().beginTransaction().add(R.id.container_live, new PlaceholderLiveFragment(150,156,140,110,160)).commit();
                        ToastUtli.show(getApplicationContext(),"获取现场报道数据失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("sucessed")) {
                                JSONObject data = jsonObject.getJSONObject("data");
                                int rw = data.getInt("rw");
                                int lg = data.getInt("lg");
                                int jj = data.getInt("jj");
                                int gj = data.getInt("gj");
                                int gl = data.getInt("gl");
                                getSupportFragmentManager().beginTransaction().add(R.id.container_live, new PlaceholderLiveFragment(rw,lg,jj,gj,gl)).commit();
                                closeProgressDialog();
                            } else {
                                ToastUtli.show(getApplicationContext(),jsonObject.getString("Msg"));
                                closeProgressDialog();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            closeProgressDialog();
                            Logs.e("获取现场报道数据失败："+e.toString());
                            ToastUtli.show(getApplicationContext(),"获取现场报道数据失败");
                        }
                    }
                });
    }

    public static class PlaceholderLiveFragment extends Fragment {
        private PieChartView chart;
        private PieChartData data;
        private int rw; // 人文
        private int lg; // 理工
        private int jj; // 经济
        private int gj; // 国交
        private int gl; // 管理

        @SuppressLint("ValidFragment")
        public PlaceholderLiveFragment(int rw, int lg, int jj, int gj, int gl) {
            this.rw = rw;
            this.lg = lg;
            this.jj = jj;
            this.gj = gj;
            this.gl = gl;
        }

        public PlaceholderLiveFragment(){}

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

            SliceValue sliceValue1 = new SliceValue(rw, ChartUtils.pickColor());
            sliceValue1.setLabel("人文学部"+rw+"人");
            values.add(sliceValue1);

            SliceValue sliceValue2 = new SliceValue(lg, ChartUtils.pickColor());
            sliceValue2.setLabel("理工学部"+lg+"人");
            values.add(sliceValue2);

            SliceValue sliceValue3 = new SliceValue(jj, ChartUtils.pickColor());
            sliceValue3.setLabel("经济学部"+jj+"人");
            values.add(sliceValue3);

            SliceValue sliceValue4 = new SliceValue(gj, ChartUtils.pickColor());
            sliceValue4.setLabel("国交学部"+gj+"人");
            values.add(sliceValue4);

            SliceValue sliceValue5 = new SliceValue(gl, ChartUtils.pickColor());
            sliceValue5.setLabel("管理学部"+gl+"人");
            values.add(sliceValue5);

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
