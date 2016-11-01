package com.hicc.cloud.teacher.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.bean.Family;
import com.hicc.cloud.teacher.bean.Student;
import com.hicc.cloud.teacher.fragment.DetailedInfoFragment;
import com.hicc.cloud.teacher.fragment.FamilyInfoFragment;
import com.hicc.cloud.teacher.utils.Logs;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.hicc.cloud.teacher.view.ScrollViewPager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/10/11/011.
 * 学生档案
 */
public class StudentProfileActivity extends AppCompatActivity {

    //private EditText et_search;
    //private ImageButton ib_search;
    private ImageView iv_back;
    private TextView tv_name;
    private TextView tv_class;
    private TextView tv_sex;
    private TextView tv_stu_num;
    private String URL = "http://suguan.hicc.cn/hicccloudt/getStudentInfo";
    private ProgressDialog progressDialog;
    //private StudentInfoDB db;
    private String stuName;
    private String stuNum;
    private String classDes;
    private String sex;
    private Student mStudent = new Student();
    private List<Family> mFamilyList = new ArrayList<>();

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    initUI();
                    tv_name.setText("姓名："+ stuName);
                    tv_sex.setText("性别：" + sex);
                    tv_stu_num.setText("学号："+ stuNum);
                    tv_class.setText("班级："+ classDes);
                    closeProgressDialog();
                    break;
                case 1:
                    closeProgressDialog();
                    tv_name.setText("姓名：");
                    tv_sex.setText("性别：");
                    tv_stu_num.setText("学号：");
                    tv_class.setText("班级：");
                    ToastUtli.show(getApplicationContext(),"学号错误，请检查无误后输入");
                    break;
                case 2:
                    closeProgressDialog();
                    ToastUtli.show(getApplicationContext(),"查询失败");
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentprofile);
        findUI();

        // 创建数据库实例
        //db = StudentInfoDB.getInstance(this);

        String studentNu = getIntent().getStringExtra("studentNu");

        showProgressDialog();
        // 查询学号   优先从数据库中查询  没有再请求网络查询
        //queryStudent(studentNu);
        queryFromServer(studentNu);

        // 当从网络查询完毕后再加载页面
        //initUI();

        // 搜索学号
        //searchNum();
    }


    /* 有搜索栏的
    private void searchNum() {
        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = et_search.getText().toString().trim();
                if(!num.equals("")){
                    showProgressDialog();
                    // 查询学号   优先从数据库中查询  没有再请求网络查询
                    queryStudent(num);
                }else{
                    ToastUtli.show(getApplicationContext(),"学号不能为空");
                }
            }
        });
    }
    */

    /* 有数据库的查询方式  为了信息安全  不使用数据库  直接网络查询
    private void queryStudent(String stuNum) {
        // 从数据库中查询
        mStudent = db.getStudent(stuNum);
        if(mStudent != null){
            Logs.i("从数据库中查询");
            tv_name.setText("姓名："+ mStudent.getStudentName());
            tv_sex.setText("性别：" + mStudent.getGenderDescription());
            tv_class.setText("班级："+ mStudent.getClassDescription());
            tv_stu_num.setText("学号："+ mStudent.getStudentNu());

            // 查询家庭信息
            mFamilyList = db.getFamilys(mStudent.getStudentNu());

            // 发送携带信息的广播
            Intent intent = new Intent();
            intent.setAction("ACTION_UPDATA_UI");
            Bundle bundle = new Bundle();
            bundle.putSerializable("mStudent", mStudent);
            intent.putExtras(bundle);
            intent.putExtra("family", (Serializable) mFamilyList);
            sendBroadcast(intent);

            closeProgressDialog();
        }else{
            Logs.i("从服务器中查询");
            // 从服务器中查询
            queryFromServer(stuNum);
        }
    }
    */

    // 网络查询
    private void queryFromServer(String stuNum) {

        // 发送GET请求
        OkHttpUtils
                .get()
                .url(URL)
                .addParams("studentNum", stuNum)
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
                        getJsonInfo(response);
                    }
                });
    }

    private void getJsonInfo(final String response) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean sucessed = jsonObject.getBoolean("sucessed");
                    if(sucessed){
                        Logs.i("开始解析");

                        JSONObject data = jsonObject.getJSONObject("data");
                        JSONObject dataInfo = data.getJSONObject("dataInfo");

                        // **学生姓名
                        stuName = dataInfo.getString("StudentName");
                        mStudent.setStudentName(stuName);
                        //tv_name.setText("姓名："+ stuName);
                        // **学号
                        stuNum = dataInfo.getString("StudentNu");
                        mStudent.setStudentNu(stuNum);
                        //tv_stu_num.setText("学号："+ stuNum);
                        // -专业
                        String professional = dataInfo.getString("ProfessionalDescription");
                        mStudent.setProfessionalDescription(professional);
                        // **性别
                        sex = dataInfo.getString("GenderDescription");
                        mStudent.setGenderDescription(sex);
                        //tv_sex.setText("性别："+sex);
                        // **班级
                        classDes = dataInfo.getString("ClassDescription");
                        mStudent.setClassDescription(classDes);
                        //tv_class.setText("班级："+ classDes);
                        // -缴费状态
                        String paymentStausDes = dataInfo.getString("PaymentStausDescription");
                        mStudent.setPaymentStausDescription(paymentStausDes);
                        // -民族
                        String nationalDes = dataInfo.getString("NationalDescription");
                        mStudent.setNationalDescription(nationalDes);
                        // -省份
                        String provinceDes = dataInfo.getString("ProvinceDescription");
                        mStudent.setProvinceDescription(provinceDes);
                        // **年级代码
                        int gradeCode = dataInfo.getInt("GradeCode");
                        mStudent.setGradeCode(gradeCode);
                        // -宿舍
                        String dormitoryDes = dataInfo.getString("DormitoryDescription");
                        mStudent.setDormitoryDescription(dormitoryDes);
                        // 宿舍号
                        if(!dataInfo.getString("DormitoryNo").equals("null")){
                            int dormitoryNo = dataInfo.getInt("DormitoryNo");
                            mStudent.setDormitoryNo(dormitoryNo);
                        }
                        // -学部
                        String division = dataInfo.getString("DivisionDescription");
                        mStudent.setDivisionDescription(division);
                        // -体重
                        String weight = dataInfo.getString("Weight");
                        mStudent.setWeight(weight);
                        // -电话
                        String phone = dataInfo.getString("YourPhone");
                        mStudent.setYourPhone(phone);
                        // -年级
                        String grade = dataInfo.getString("GradeDescription");
                        mStudent.setGradeDescription(grade);
                        // -床号
                        String bedNumber = dataInfo.getString("BedNumber");
                        mStudent.setBedNumber(bedNumber);
                        // -身高
                        String height = dataInfo.getString("Height");
                        mStudent.setHeight(height);
                        // **毕业学校
                        String oldSchool = dataInfo.getString("OldSchool");
                        mStudent.setOldSchool(oldSchool);
                        // -生日
                        String birthDate = dataInfo.getString("BirthDate");
                        mStudent.setBirthDate(birthDate);
                        // -身份证号
                        String idNumber = dataInfo.getString("IdNumber");
                        mStudent.setIdNumber(idNumber);
                        // **入学时间
                        String inTime = dataInfo.getString("EnrollmentDate");
                        mStudent.setEnrollmentDate(inTime);
                        // -家庭住址
                        String homeAddress = dataInfo.getString("HomeAddress");
                        mStudent.setHomeAddress(homeAddress);
                        // -政治面貌
                        String politicsStatusDes = dataInfo.getString("PoliticsStatusDescription");
                        mStudent.setPoliticsStatusDescription(politicsStatusDes);
                        // -籍贯
                        String nativePlace = dataInfo.getString("NativePlace");
                        mStudent.setNativePlace(nativePlace);
                        // -现场报道
                        String liveReportStatueDes = dataInfo.getString("LiveReportStatueDescription");
                        mStudent.setLiveReportStatueDescription(liveReportStatueDes);
                        // -网上报道
                        String onlineReportStatueDes = dataInfo.getString("OnlineReportStatueDescription");
                        mStudent.setOnlineReportStatueDescription(onlineReportStatueDes);
                        // **班级代码
                        /*int classCode = db.getClasCodeForDB(classDes,gradeCode);
                        if(classCode >= 0){
                            mStudent.setClassCode(classCode);
                        }*/
                        mStudent.setClassCode(dataInfo.getInt("ClassId"));

                        // 存到数据库  为了数据安全  取消
                        //db.saveStudent(student);

                        Logs.i("解析家庭信息");

                        // 解析家庭信息
                        JSONArray dataFamily = data.getJSONArray("dataFamily");
                        for(int i=0; i < dataFamily.length(); i++){
                            Family family = new Family();

                            JSONObject familyInfo = dataFamily.getJSONObject(i);
                            // 学号
                            String stuNu = familyInfo.getString("StudentNu");
                            family.setStudentNum(stuNu);
                            // 姓名
                            String name = familyInfo.getString("Name");
                            family.setName(name);
                            // 工作
                            String workandPosition = familyInfo.getString("WorkandPosition");
                            family.setWorkand(workandPosition);
                            // 关系
                            String relation = familyInfo.getString("Relation");
                            family.setRelation(relation);
                            // 电话
                            String familyPhone = familyInfo.getString("Phone");
                            family.setPhone(familyPhone);
                            // 年龄
                            int age = familyInfo.getInt("Age");
                            family.setAge(age);
                            // 政治面貌
                            String politicsStatus = familyInfo.getString("PoliticsStatus");
                            family.setPolitics(politicsStatus);
                            // 联系地址
                            String contactAddress = familyInfo.getString("ContactAddress");
                            family.setContactAddress(contactAddress);

                            // 存到数据库  为了数据安全  取消
                            //db.saveFamily(family);
                            mFamilyList.add(family);
                        }

                        Logs.i("发handler消息");

                        mHandler.sendEmptyMessage(0);

                        /*
                        // 发送携带信息的广播
                        Intent intent = new Intent();
                        intent.setAction("ACTION_UPDATA_UI");
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mStudent", mStudent);
                        intent.putExtras(bundle);
                        intent.putExtra("family", (Serializable)mFamilyList);
                        sendBroadcast(intent);
                        */
                    }else{
                        // 查不到  学号或服务器错误
                        mHandler.sendEmptyMessage(1);
                    }
                } catch (JSONException e) {
                    // 解析错误
                    mHandler.sendEmptyMessage(2);
                    e.printStackTrace();
                }
            }
        }.start();
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

    private void findUI() {
        // 返回
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //et_search = (EditText) findViewById(R.id.et_search);
        //ib_search = (ImageButton) findViewById(R.id.ib_search);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_class = (TextView) findViewById(R.id.tv_class);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_stu_num = (TextView) findViewById(R.id.tv_stu_num);
    }
    private void initUI() {
        // 设置viewpager
        ScrollViewPager viewPager = (ScrollViewPager) findViewById(R.id.viewpager);
        // 设置viewpager是否禁止滑动
        viewPager.setNoScroll(false);
        setupViewPager(viewPager);

        // 设置tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#9AB4E2"));
        tabLayout.setupWithViewPager(viewPager);
    }

    // 设置viewpager
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new DetailedInfoFragment(mStudent), "详细信息");
        adapter.addFrag(new FamilyInfoFragment(mFamilyList), "家庭信息");
        //adapter.addFrag(new EducationInfoFragment(), "教育经历");

        viewPager.setAdapter(adapter);
    }

    // viewPager适配器
    static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    // TODO 更改为listview的方式  先展示导员所带班级列表  每个item的点击事件为跳转到相应的班级学生列表(也是用listview)  学生列表的item点击事件才是跳转到学生信息界面
    // 1.找到相应控件
    // 2.编写listview适配器
    // 3.发网络请求  获取导员所带班级信息
    // 4.解析json数据  并保存到List集合中
    // 5.得到集合后  给listview设置适配器
}
