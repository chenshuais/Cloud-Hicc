package com.hicc.cloud.teacher.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.adapter.MyBaseAdapter;
import com.hicc.cloud.teacher.bean.Mark;
import com.hicc.cloud.teacher.utils.Logs;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by 野 on 2016/10/13.
 */

public class MarkHistoryTermFragment extends BaseFragment {

    private List<Mark> listObj = new ArrayList<Mark>();
    private MyBaseAdapter adapter;

    @Override
    public void fetchData() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historymark, container, false);
        IntentFilter filter = new IntentFilter();
        filter.addAction("SET_DATA");
        getContext().registerReceiver(myReceiver, filter);
        ListView listview = (ListView) view.findViewById(R.id.lv_mark);
        adapter = new MyBaseAdapter(listObj);
        listview.setAdapter(adapter);
        return view;
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Logs.i("123123");
            listObj = (ArrayList<Mark>) intent.getSerializableExtra("marklist");
            adapter.notifyDataSetChanged();
        }

    };

    class MyBaseAdapter extends BaseAdapter {
        private List<Mark> mList;

        MyBaseAdapter(List<Mark> mList) {
            this.mList = mList;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Mark getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(getApplicationContext(), R.layout.item_historymark, null);

                viewHolder = new ViewHolder();
                viewHolder.tv_course = (TextView) convertView.findViewById(R.id.tv_course);
                viewHolder.tv_mark = (TextView) convertView.findViewById(R.id.tv_mark);
                viewHolder.tv_teacher = (TextView) convertView.findViewById(R.id.tv_teacher);

                // 将存有findViewById的viewHolder存到convertView中
                convertView.setTag(viewHolder);
            } else {
                // 复用convertView里存的viewHolder
                viewHolder = (ViewHolder) convertView.getTag();

            }

            viewHolder.tv_teacher.setText(getItem(position).getTeacher());
            viewHolder.tv_mark.setText(getItem(position).getMark());
            viewHolder.tv_course.setText(getItem(position).getCourse());
            return null;
        }


    }

    static class ViewHolder {

        TextView tv_mark;
        TextView tv_teacher;
        TextView tv_course;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (myReceiver != null) {
            getContext().unregisterReceiver(myReceiver);
        }
    }
}
