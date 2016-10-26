package com.hicc.cloud.teacher.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.bean.Student;
import com.hicc.cloud.teacher.db.StudentInfoDB;
import com.hicc.cloud.teacher.utils.Logs;

import java.util.List;

/**
 * Created by Administrator on 2016/10/26/026.
 */
public class StudentListActivity extends AppCompatActivity {
    private List<Student> studentList;
    private ImageView iv_back;
    private ProgressDialog progressDialog;
    private ListView lv_student;
    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);
        int classCode = getIntent().getIntExtra("classcode",0);
        type = getIntent().getIntExtra("type",0);
        initUI();

        initData(classCode);
    }

    private void initData(final int classCode) {
        final StudentInfoDB db = StudentInfoDB.getInstance(this);
        showProgressDialog();
        new Thread(){
            @Override
            public void run() {
                studentList = db.getStudents(classCode);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Logs.i("大小是："+studentList.size());
                        lv_student.setAdapter(new MyAdapter());
                        closeProgressDialog();
                    }
                });
            }
        }.start();


    }

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv_student = (ListView) findViewById(R.id.lv_student);

        lv_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Logs.i(studentList.get(position).getStudentNu());
                // 学生档案
                if(type == 1){
                    Intent intent = new Intent(getApplicationContext(),StudentProfileActivity.class);
                    intent.putExtra("studentNu",studentList.get(position).getStudentNu());
                    startActivity(intent);
                // 学生成绩
                }else if(type == 2){

                }

            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return studentList.size();
        }

        @Override
        public Student getItem(int position) {
            return studentList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ClassListActivity.ViewHoulder viewHoulder;
            if(convertView == null){
                convertView = View.inflate(getApplicationContext(),R.layout.item_class,null);
                viewHoulder = new ClassListActivity.ViewHoulder();
                viewHoulder.tv_classdes = (TextView) convertView.findViewById(R.id.tv_classdes);
                convertView.setTag(viewHoulder);
            }
            viewHoulder = (ClassListActivity.ViewHoulder) convertView.getTag();
            viewHoulder.tv_classdes.setText(getItem(position).getStudentName());

            return convertView;
        }
    }

    static class ViewHoulder {
        TextView tv_classdes;
    }

    // 显示进度对话框
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("加载中...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    return;
                }
            });
        }
        progressDialog.show();
    }

    // 关闭进度对话框
    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
