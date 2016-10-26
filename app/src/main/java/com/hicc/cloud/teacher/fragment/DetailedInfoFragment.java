package com.hicc.cloud.teacher.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.bean.Student;
import com.hicc.cloud.teacher.utils.Logs;

/**
 * Created by Administrator on 2016/9/24/024.
 */

@SuppressLint("ValidFragment")
public class DetailedInfoFragment extends BaseFragment {
    private MyBroadcastReceiver mBroadcastReceiver;
    private Student mStudent;
    private TextView tv_grade;
    private TextView tv_division;
    private TextView tv_professional;
    private TextView tv_phone;
    private TextView tv_dormitory;
    private TextView tv_bednumber;
    private TextView tv_national;
    private TextView tv_province;
    private TextView tv_nativeplace;
    private TextView tv_politics;
    private TextView tv_homeaddress;
    private TextView tv_idnumber;
    private TextView tv_birthdate;
    private TextView tv_height;
    private TextView tv_weight;
    private TextView tv_paymentstaus;
    private TextView tv_onlinereport;
    private TextView tv_liveseportstatue;

    private String grade = "";
    private String division = "";
    private String professional = "";
    private String phone = "";
    private String dormitory = "";
    private int dormitoryNo = 0;
    private String bednumber = "";
    private String national = "";
    private String province = "";
    private String nativeplace = "";
    private String politics = "";
    private String homeaddress = "";
    private String idnumber = "";
    private String birthdate = "";
    private String height = "";
    private String weight = "";
    private String paymentstaus = "";
    private String onlinereport = "";
    private String liveseportstatue = "";

    @SuppressLint("ValidFragment")
    public DetailedInfoFragment(Student student){
        mStudent = student;
    }
    @Override
    public void fetchData() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detailed_info, container, false);

        initUI(view);

        initData();

        setUI();

        // 动态注册广播
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ACTION_UPDATA_UI");
        getContext().registerReceiver(mBroadcastReceiver, intentFilter);

        return view;
    }

    private void initData() {
        grade = mStudent.getGradeDescription();
        division = mStudent.getDivisionDescription();
        professional = mStudent.getProfessionalDescription();
        phone = mStudent.getYourPhone();
        dormitory = mStudent.getDormitoryDescription();
        dormitoryNo = mStudent.getDormitoryNo();
        bednumber = mStudent.getBedNumber();
        national = mStudent.getNationalDescription();
        province = mStudent.getProvinceDescription();
        nativeplace = mStudent.getNativePlace();
        politics = mStudent.getPoliticsStatusDescription();
        homeaddress = mStudent.getHomeAddress();
        idnumber = mStudent.getIdNumber();
        birthdate = mStudent.getBirthDate();
        birthdate = birthdate.substring(0,10);
        height = mStudent.getHeight();
        weight = mStudent.getWeight();
        paymentstaus = mStudent.getPaymentStausDescription();
        onlinereport = mStudent.getOnlineReportStatueDescription();
        liveseportstatue = mStudent.getLiveReportStatueDescription();
    }

    private void setUI() {
        tv_grade.setText("年级："+grade);
        tv_division.setText("学部："+division);
        tv_professional.setText("专业："+professional);
        tv_phone.setText("电话："+phone);
        tv_dormitory.setText("宿舍："+ dormitory + " " + dormitoryNo);
        tv_bednumber.setText("床号："+bednumber);
        tv_national.setText("民族："+national);
        tv_province.setText("省份："+province);
        tv_nativeplace.setText("籍贯："+nativeplace);
        tv_politics.setText("政治面貌："+politics);
        tv_homeaddress.setText("家庭住址："+homeaddress);
        tv_idnumber.setText("身份证号："+idnumber);
        tv_birthdate.setText("出生日期："+birthdate);
        tv_height.setText("身高："+height);
        tv_weight.setText("体重："+weight);
        tv_paymentstaus.setText("缴费状态："+paymentstaus);
        tv_onlinereport.setText("网上报到："+onlinereport);
        tv_liveseportstatue.setText("现场报到："+liveseportstatue);
    }

    private void initUI(View view) {
        tv_grade = (TextView) view.findViewById(R.id.tv_grade);
        tv_division = (TextView) view.findViewById(R.id.tv_division);
        tv_professional = (TextView) view.findViewById(R.id.tv_professional);
        tv_phone = (TextView) view.findViewById(R.id.tv_phone);
        tv_dormitory = (TextView) view.findViewById(R.id.tv_dormitory);
        tv_bednumber = (TextView) view.findViewById(R.id.tv_bednumber);
        tv_national = (TextView) view.findViewById(R.id.tv_national);
        tv_province = (TextView) view.findViewById(R.id.tv_province);
        tv_nativeplace = (TextView) view.findViewById(R.id.tv_nativeplace);
        tv_politics = (TextView) view.findViewById(R.id.tv_politics);
        tv_homeaddress = (TextView) view.findViewById(R.id.tv_homeaddress);
        tv_idnumber = (TextView) view.findViewById(R.id.tv_idnumber);
        tv_birthdate = (TextView) view.findViewById(R.id.tv_birthdate);
        tv_height = (TextView) view.findViewById(R.id.tv_height);
        tv_weight = (TextView) view.findViewById(R.id.tv_weight);
        tv_paymentstaus = (TextView) view.findViewById(R.id.tv_paymentstaus);
        tv_onlinereport = (TextView) view.findViewById(R.id.tv_onlinereport);
        tv_liveseportstatue = (TextView) view.findViewById(R.id.tv_liveseportstatue);

        tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到拨号界面
                TextView tv = (TextView) v;
                String number = tv.getText().toString();
                number = number.substring(3);
                Logs.i("电话为："+number);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+number));
                startActivity(intent);
            }
        });
    }

    class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Logs.i("详细信息界面收到广播了吗？？？？？？");
            Student student = (Student) intent.getSerializableExtra("student");

            grade = student.getGradeDescription();
            division = student.getDivisionDescription();
            professional = student.getProfessionalDescription();
            phone = student.getYourPhone();
            dormitory = student.getDormitoryDescription();
            dormitoryNo = student.getDormitoryNo();
            bednumber = student.getBedNumber();
            national = student.getNationalDescription();
            province = student.getProvinceDescription();
            nativeplace = student.getNativePlace();
            politics = student.getPoliticsStatusDescription();
            homeaddress = student.getHomeAddress();
            idnumber = student.getIdNumber();
            birthdate = student.getBirthDate();
            birthdate = birthdate.substring(0,10);
            height = student.getHeight();
            weight = student.getWeight();
            paymentstaus = student.getPaymentStausDescription();
            onlinereport = student.getOnlineReportStatueDescription();
            liveseportstatue = student.getLiveReportStatueDescription();

            setUI();
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
