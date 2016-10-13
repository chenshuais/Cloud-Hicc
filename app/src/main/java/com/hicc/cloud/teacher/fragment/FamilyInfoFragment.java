package com.hicc.cloud.teacher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hicc.cloud.R;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class FamilyInfoFragment extends BaseFragment {

    @Override
    public void fetchData() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_family_info, container, false);

        return view;
    }
}
