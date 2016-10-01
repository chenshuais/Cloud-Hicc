package com.hicc.cloud.teacher.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.bean.Firend;

import java.util.List;

public class FirendAdapter extends ArrayAdapter<Firend> {
    private int resourceId;

    public FirendAdapter(Context context, int textViewResourceId, List<Firend> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Firend firend = getItem(position);
        ViewHolder viewHolder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_friend = (ImageView) convertView.findViewById(R.id.firend_image);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.firend_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.iv_friend.setImageResource(firend.getImageId());
        viewHolder.tv_name.setText(firend.getName());
        viewHolder.tv_phone.setText(firend.getPhone());
        viewHolder.tv_time.setText(firend.getTime());

        return convertView;
    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_phone;
        TextView tv_time;
        ImageView iv_friend;
    }

}
