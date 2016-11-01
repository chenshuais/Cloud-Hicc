package com.hicc.cloud.teacher.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.adapter.TimeLineAdapter;
import com.hicc.cloud.teacher.bean.Student;
import com.hicc.cloud.teacher.bean.TimeLineModel;
import com.hicc.cloud.teacher.utils.Logs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24/024.
 * 学生档案  教育经历
 */

public class EducationInfoFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private boolean isSee = true;
    private MyBroadcastReceiver mBroadcastReceiver;
    private String date = "";
    private String oldSchool = "";
    private TimeLineModel model;
    private TimeLineModel model2;

    @Override
    public void fetchData() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education_info, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        initDate();

        // 动态注册广播
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION_UPDATA_UI");
        getContext().registerReceiver(mBroadcastReceiver, intentFilter);

        return view;
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logs.i("收到广播了吗？？？？？？");
            Student student = (Student) intent.getSerializableExtra("student");
            date = student.getEnrollmentDate();
            oldSchool = student.getOldSchool();

            String subDate = "";
            if (!date.equals("")) {
                subDate = date.substring(0, 4);
                Logs.i("截取字符串：" + subDate);
            } else {
                subDate = "2015";
            }
            int da = Integer.valueOf(subDate);
            da = da - 3;

            model.setTime(subDate + ".9 — 至今");
            model.setScool("河北大学工商学院");

            model2.setTime(da + ".9 — " + subDate + ".6");
            model2.setScool(oldSchool);

            mTimeLineAdapter.notifyDataSetChanged();
        }
    }

    private LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        return linearLayoutManager;
    }

    private void initDate() {
        if (isSee) {
            String subDate = "";
            if (!date.equals("")) {
                subDate = date.substring(0, 4);
                Logs.i("截取字符串：" + subDate);
            } else {
                subDate = "2015";
            }
            int da = Integer.valueOf(subDate);
            da = da - 3;
            model = new TimeLineModel();
            model.setTime(subDate + ".9 — 至今");
            model.setScool("河北大学工商学院");
            mDataList.add(model);

            model2 = new TimeLineModel();
            model2.setTime(da + ".9 — " + subDate + ".6");
            model2.setScool(oldSchool);
            mDataList.add(model2);

            mTimeLineAdapter = new TimeLineAdapter(mDataList);
            mRecyclerView.setAdapter(mTimeLineAdapter);
            isSee = false;
        } else {
            mTimeLineAdapter = new TimeLineAdapter(mDataList);
            mRecyclerView.setAdapter(mTimeLineAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 取消注册广播
        if (mBroadcastReceiver != null) {
            Logs.i("取消注册广播");
            getContext().unregisterReceiver(mBroadcastReceiver);
        }
    }
}
