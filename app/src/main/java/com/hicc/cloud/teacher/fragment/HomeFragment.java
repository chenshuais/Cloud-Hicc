package com.hicc.cloud.teacher.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.activity.AllActivity;
import com.hicc.cloud.teacher.activity.ClassCheckActivity;
import com.hicc.cloud.teacher.activity.ClassGrowUpActivity;
import com.hicc.cloud.teacher.activity.DormitoryScoreActivity;
import com.hicc.cloud.teacher.activity.LeaveBackActivity;
import com.hicc.cloud.teacher.activity.ScanActivity;
import com.hicc.cloud.teacher.activity.ShakeActivity;
import com.hicc.cloud.teacher.activity.StudentCommunityActivity;
import com.hicc.cloud.teacher.activity.StudentProfileActivity;
import com.hicc.cloud.teacher.bean.Picture;
import com.hicc.cloud.teacher.utils.ToastUtli;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24/024.
 * Alter by i_cassell on 2016/9/25.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private static final int SCAN_CODE = 0;
    private GridView gridView;
    private String[] titles = new String[]{"学生成绩", "宿舍成绩", "请销假", "课堂签到", "学生社团", "班级成长", "学生档案", "全部"};
    private int[] images = new int[]{ R.drawable.icon_stu_ach, R.drawable.icon_room_ach,
            R.mipmap.leaveback, R.drawable.icon_check,
            R.mipmap.club, R.mipmap.classes,
            R.drawable.icon_file, R.mipmap.icon_all};
    private LinearLayout ll_scan;
    private LinearLayout ll_shake;
    private LinearLayout ll_record;

    // 加载数据
    @Override
    public void fetchData() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initUI(view);

        PictureAdapter adapter = new PictureAdapter(titles, images, getContext());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //未设置点击事件
                switch (position){
                    case 0:
                        break;
                    case 1:
                        startActivity(new Intent(getContext(),DormitoryScoreActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(),LeaveBackActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(),ClassCheckActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext(),StudentCommunityActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(getContext(),ClassGrowUpActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(getContext(),StudentProfileActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(getContext(),AllActivity.class));
                        break;
                }
            }
        });

        return view;
    }

    private void initUI(View view) {
        gridView = (GridView) view.findViewById(R.id.gv_menu);
        ll_scan = (LinearLayout) view.findViewById(R.id.ll_scan);
        ll_shake = (LinearLayout) view.findViewById(R.id.ll_shake);
        ll_record = (LinearLayout) view.findViewById(R.id.ll_record);

        ImageView iv_scan = (ImageView) view.findViewById(R.id.iv_scan);
        ImageView iv_shake = (ImageView) view.findViewById(R.id.iv_shake);
        ImageView iv_record = (ImageView) view.findViewById(R.id.iv_record);

        ll_scan.setOnClickListener(this);
        ll_shake.setOnClickListener(this);
        ll_record.setOnClickListener(this);

        iv_scan.setOnClickListener(this);
        iv_shake.setOnClickListener(this);
        iv_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_scan:
            case R.id.iv_scan:
                startActivityForResult(new Intent(getContext(), ScanActivity.class),SCAN_CODE);
                break;
            case R.id.ll_shake:
            case R.id.iv_shake:
                startActivity(new Intent(getContext(), ShakeActivity.class));
                break;
            case R.id.ll_record:
            case R.id.iv_record:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == SCAN_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    // TODO 解析后操作
                    ToastUtli.show(getContext(),"解析结果:" + result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtli.show(getContext(),"解析二维码失败");
                }
            }
        }
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
            if(position == 1 || position == 2 || position == 3 || position == 4 || position == 5){
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
