package com.hicc.cloud.teacher.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.adapter.TimeLineAdapter;
import com.hicc.cloud.teacher.bean.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class EducationInfoFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private boolean isSee = true;

    @Override
    public void fetchData() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education_info, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(getLinearLayoutManager());
        mRecyclerView.setHasFixedSize(true);

        initDate();

        return view;
    }

    private LinearLayoutManager getLinearLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        return linearLayoutManager;
    }

    private void initDate() {
        if(isSee){
            TimeLineModel model = new TimeLineModel();
            model.setTime("2015.9 — 至今");
            model.setScool("河北大学工商学院");
            mDataList.add(model);

            TimeLineModel model2 = new TimeLineModel();
            model2.setTime("2012.9 — 2015.6");
            model2.setScool("河北安新中学");
            mDataList.add(model2);

            mTimeLineAdapter = new TimeLineAdapter(mDataList);
            mRecyclerView.setAdapter(mTimeLineAdapter);
            isSee = false;
        }else {
            mTimeLineAdapter = new TimeLineAdapter(mDataList);
            mRecyclerView.setAdapter(mTimeLineAdapter);
        }

    }
}
