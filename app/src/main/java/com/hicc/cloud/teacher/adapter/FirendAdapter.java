package com.hicc.cloud.teacher.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.db.Firend;

import java.util.List;

public class FirendAdapter extends ArrayAdapter<Firend> {
    private int resourceId;

    public FirendAdapter(Context context, int textViewResourceId,
                        List<Firend> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Firend firend = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView fruitImage = (ImageView) view.findViewById(R.id.firend_image);
        TextView fruitName = (TextView) view.findViewById(R.id.firend_name);
        fruitImage.setImageResource(firend.getImageId());
        fruitName.setText(firend.getName());
        return view;
    }

}
