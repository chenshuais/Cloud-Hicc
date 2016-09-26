package com.hicc.cloud.teacher.fragment;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hicc.cloud.R;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class InformationFragment extends BaseFragment {
    public ImageView picture;
    public TextView name;
    public TextView position;
    public TextView phone;
    public TextView set;
    public TextView department;
    public Button esc;

    @Override
    public void fetchData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_information, container, false);
        initUI(view);

        return view;
    }

    private void initUI(View view) {
        picture = (ImageView) view.findViewById(R.id.picture);
        name = (TextView) view.findViewById(R.id.name);
        position = (TextView) view.findViewById(R.id.position);
        phone = (TextView) view.findViewById(R.id.phone);
        set = (TextView) view.findViewById(R.id.set);
        esc = (Button) view.findViewById(R.id.esc);
        department = (TextView) view.findViewById(R.id.department);
    }

}
