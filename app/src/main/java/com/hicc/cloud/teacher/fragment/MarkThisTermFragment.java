package com.hicc.cloud.teacher.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hicc.cloud.R;

/**
 * Created by 野 on 2016/10/13.
 */

public class MarkThisTermFragment extends BaseFragment {


    @Override
    public void fetchData() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family_info, container, false);

        return view;
    }
}

