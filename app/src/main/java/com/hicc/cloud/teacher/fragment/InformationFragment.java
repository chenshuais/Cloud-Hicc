package com.hicc.cloud.teacher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class InformationFragment extends BaseFragment implements View.OnClickListener{
    public ImageView ivPicture;
    public TextView tvName;
    public TextView tvPosition;
    public TextView tvPhone;
    public TextView tvSet;
    public TextView tvPepartment;
    public Button btEsc;


    @Override
    public void fetchData() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.information_fragment, container, false);
        initUI(view);

        return view;
    }
    private void initUI(View view) {
        ivPicture = (ImageView) view.findViewById(R.id.picture);
        tvName = (TextView) view.findViewById(R.id.name);
        tvPosition = (TextView) view.findViewById(R.id.position);
        tvPhone = (TextView) view.findViewById(R.id.phone);
        tvSet = (TextView) view.findViewById(R.id.set);
        btEsc = (Button) view.findViewById(R.id.esc);
        tvPepartment = (TextView) view.findViewById(R.id.department);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.picture:
                break;
            case R.id.name:
                break;
            case R.id.position:
                break;
            case R.id.phone:
                break;
            case R.id.set:
                break;
            case R.id.esc:
                break;
            case R.id.department:
                break;
            default:
                break;
        }
    }
}
