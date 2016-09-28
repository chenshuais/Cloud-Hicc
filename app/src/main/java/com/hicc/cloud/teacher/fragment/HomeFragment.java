package com.hicc.cloud.teacher.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24/024.
 * Alter by i_cassell on 2016/9/25.
 */

public class HomeFragment extends BaseFragment {
    private GridView gridView;
    private String[] titles = new String[]{"学生成绩", "宿舍成绩", "请销假", "课堂签到", "学生社团", "班级成长", "学生档案", "全部"};
    private int[] images = new int[]{R.drawable.icon_stu_ach, R.drawable.icon_room_ach, R.mipmap.leaveback, R.drawable.icon_check, R.mipmap.
            club, R.mipmap.classes, R.mipmap.files, R.mipmap.icon_all};

    @Override
    public void fetchData() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);
        gridView = (GridView) view.findViewById(R.id.gv_menu);
        PictureAdapter adapter = new PictureAdapter(titles, images, getContext());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //未设置点击事件
            }
        });

        return view;
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
            if(position == 2 || position == 3 || position == 4 || position == 5){
                viewHolder.title.setTextColor(Color.parseColor("#d5d2d2"));

            }
            return convertView;
        }

    }

    class ViewHolder {
        public TextView title;
        public ImageView image;
    }

    class Picture {
        private String title;
        private int imageId;

        public Picture() {
            super();
        }

        public Picture(String title, int imageId) {
            super();
            this.title = title;
            this.imageId = imageId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getImageId() {
            return imageId;
        }

        public void setImageId(int imageId) {
            this.imageId = imageId;
        }
    }
}
