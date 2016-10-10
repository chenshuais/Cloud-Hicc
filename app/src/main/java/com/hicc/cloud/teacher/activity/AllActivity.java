package com.hicc.cloud.teacher.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.bean.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/8/008.
 */
public class AllActivity extends AppCompatActivity {

    private ImageView iv_back;
    private GridView gridView;
    private String[] titles = new String[]{"学生成绩", "宿舍成绩", "请销假", "课堂签到", "学生社团", "班级成长", "学生档案", "学生缴费"};
    private int[] images = new int[]{ R.drawable.icon_stu_ach, R.drawable.icon_room_ach,
            R.mipmap.leaveback, R.drawable.icon_check,
            R.mipmap.club, R.mipmap.classes,
            R.drawable.icon_file, R.drawable.icon_payment};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        initUI();
    }

    private void initUI() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        gridView = (GridView) findViewById(R.id.gv_menu);

        PictureAdapter adapter = new PictureAdapter(titles, images, getApplicationContext());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //未设置点击事件
                switch (position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        startActivity(new Intent(getApplicationContext(),LeaveBackActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getApplicationContext(),ClassCheckActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getApplicationContext(),StudentCommunityActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getApplicationContext(),ClassGrowUpActivity.class));
                        break;
                    case 6:
                        break;
                    case 7:
                        startActivity(new Intent(getApplicationContext(),PaymentActivity.class));
                        break;
                }
            }
        });
    }

    //自定义适配器
    class PictureAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<Picture> pictures;

        public PictureAdapter(String[] titles, int[] images, Context context) {
            super();
            pictures = new ArrayList<Picture>();
            inflater = LayoutInflater.from(context);
            for (int i = 0; i < images.length; i++) {
                Picture picture = new Picture(titles[i], images[i]);
                pictures.add(picture);
            }
        }

        @Override
        public int getCount() {
            if (null != pictures) {
                return pictures.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.home_item, null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.title.setText(pictures.get(position).getTitle());
            viewHolder.image.setImageResource(pictures.get(position).getImageId());
            if(position == 1 || position == 2 || position == 3 || position == 4 || position == 5 || position == 7){
                viewHolder.title.setTextColor(Color.parseColor("#d5d2d2"));
            }
            return convertView;
        }
    }

    class ViewHolder {
        public TextView title;
        public ImageView image;
    }
}
