package com.hicc.cloud.teacher.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.adapter.FirendAdapter;
import com.hicc.cloud.teacher.bean.Firend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class FriendFragment extends BaseFragment {
    private List<Firend> firendList = new ArrayList<Firend>();
    private ListView listView;
    private LinearLayout ll_progress;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FirendAdapter adapter = new FirendAdapter(getActivity(), R.layout.firend_item, firendList);
            listView.setAdapter(adapter);
            ll_progress.setVisibility(View.GONE);
        }
    };

    @Override
    public void fetchData() {
        initFriends();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firend, container, false);

        listView = (ListView) view.findViewById(R.id.list_view);
        ll_progress = (LinearLayout) view.findViewById(R.id.ll_progress);
        ll_progress.setVisibility(View.VISIBLE);


        return view;
    }

    private void initFriends() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Firend a = new Firend("李老师", "13400362548", "9月26日 10:20", R.drawable.ic_test_friend1);
                firendList.add(a);
                Firend b = new Firend("张老师", "15512654872", "9月25日 12:20", R.drawable.ic_test_friend2);
                firendList.add(b);
                Firend c = new Firend("王老师", "13363254582", "9月24日 15:20", R.drawable.ic_test_friend12);
                firendList.add(c);
                Firend d = new Firend("小崔", "18615245789", "9月23日 09:24", R.drawable.ic_test_friend3);
                firendList.add(d);
                Firend e = new Firend("小王", "15547856955", "9月22日 10:11", R.drawable.ic_test_friend4);
                firendList.add(e);
                Firend f = new Firend("小陈", "13544648522", "9月21日 16:28", R.drawable.ic_test_friend5);
                firendList.add(f);
                Firend g = new Firend("赵老师", "15231565455", "9月20日 06:24", R.drawable.ic_test_friend6);
                firendList.add(g);
                Firend h = new Firend("于老师", "13400254785", "9月19日 09:50", R.drawable.ic_test_friend7);
                firendList.add(h);
                Firend i = new Firend("周老师", "13673219955", "9月19日 06:55", R.drawable.ic_test_friend8);
                firendList.add(i);
                Firend j = new Firend("小田", "18773125475", "9月18日 15:13", R.drawable.ic_test_friend9);
                firendList.add(j);
                Firend k = new Firend("丛老师", "15532565425", "9月17日 09:23", R.drawable.ic_test_friend10);
                firendList.add(k);
                Firend l = new Firend("田老师", "18012354789", "9月16日 10:11", R.drawable.ic_test_friend11);
                firendList.add(l);
                mHandler.sendEmptyMessage(0);
            }
        }.start();
    }
}
