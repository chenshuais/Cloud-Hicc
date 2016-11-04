package com.hicc.cloud.teacher.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.activity.AboutWeActivity;
import com.hicc.cloud.teacher.activity.FeedBackActivity;
import com.hicc.cloud.teacher.activity.LogInActivity;
import com.hicc.cloud.teacher.activity.SettingActivity;
import com.hicc.cloud.teacher.bean.ExitEvent;
import com.hicc.cloud.teacher.utils.ConstantValue;
import com.hicc.cloud.teacher.utils.SpUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class InformationFragment extends BaseFragment implements View.OnClickListener{
    public ImageView ivPicture;
    public TextView tvName;
    public TextView tvPosition;
    public TextView tvPhone;
    public Button btEsc;
    private TextView tvLevel;


    @Override
    public void fetchData() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        initUI(view);

        return view;
    }
    private void initUI(View view) {
        // 头像
        ivPicture = (ImageView) view.findViewById(R.id.picture);
        // 姓名
        tvName = (TextView) view.findViewById(R.id.name);
        // 职位
        tvLevel = (TextView) view.findViewById(R.id.tv_level);
        // 电话
        tvPhone = (TextView) view.findViewById(R.id.phone);
        // 注销按钮
        btEsc = (Button) view.findViewById(R.id.esc);
        // 设置
        LinearLayout ll_setting = (LinearLayout) view.findViewById(R.id.ll_setting);
        // 关于
        LinearLayout ll_info = (LinearLayout) view.findViewById(R.id.ll_info);
        // 反馈
        LinearLayout ll_feedback = (LinearLayout) view.findViewById(R.id.ll_feedback);

        btEsc.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
        ll_feedback.setOnClickListener(this);
        ll_info.setOnClickListener(this);

        tvName.setText(SpUtils.getStringSp(getContext(), ConstantValue.TEACHER_NAME,""));
        tvLevel.setText("职位："+SpUtils.getStringSp(getContext(), ConstantValue.TEACHER_LEVEL,""));
        tvPhone.setText("电话："+SpUtils.getStringSp(getContext(),ConstantValue.TEACHER_PHONE,""));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            // 设置
            case R.id.ll_setting:
                startActivity(new Intent(getContext(),SettingActivity.class));
                break;
            // 反馈
            case R.id.ll_feedback:
                startActivity(new Intent(getContext(),FeedBackActivity.class));
                break;
            // 关于
            case R.id.ll_info:
                startActivity(new Intent(getContext(),AboutWeActivity.class));
                break;
            // 注销按钮
            case R.id.esc:
                EventBus.getDefault().post(new ExitEvent());
                startActivity(new Intent(getContext(), LogInActivity.class));
                SpUtils.remove(getContext(),ConstantValue.USER_NAME);
                SpUtils.remove(getContext(),ConstantValue.PASS_WORD);
                SpUtils.remove(getContext(),ConstantValue.TEACHER_NAME);
                SpUtils.remove(getContext(),ConstantValue.TEACHER_LEVEL);
                SpUtils.remove(getContext(),ConstantValue.TEACHER_PHONE);
                SpUtils.putBoolSp(getContext(),ConstantValue.IS_REMBER_PWD,false);
                break;
        }
    }
}
