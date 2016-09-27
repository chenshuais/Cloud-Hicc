package com.hicc.cloud.teacher.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.hicc.cloud.R;
import com.hicc.cloud.teacher.db.Firend;
import com.hicc.cloud.teacher.adapter.FirendAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24/024.
 */

public class FriendFragment extends BaseFragment {
    private List<Firend> firendList = new ArrayList<Firend>();
    @Override
    public void fetchData() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_firend, container, false);
        initFriends();
        FirendAdapter adapter = new FirendAdapter(
                getActivity(), R.layout.firend_item, firendList);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        return view;
    }
    private void initFriends() {
    Firend a = new Firend("李老师","今天下午在B2—212开全体大会","9月26日 10:20", R.drawable.ic_test_friend1);
    firendList.add(a);
    Firend b = new Firend("张老师","今天中午一起吃个饭","9月25日 12:20", R.drawable.ic_test_friend2);
    firendList.add(b);
    Firend c = new Firend("王老师","明天去市里，大家把最近的工作状态汇报一下，做个总结，然后一起吃个饭。","9月24日 15:20", R.drawable.ic_test_friend12);
    firendList.add(c);
    Firend d = new Firend("小鸿","老师，最近有点事情","9月23日 09:24", R.drawable.ic_test_friend3);
    firendList.add(d);
    Firend e = new Firend("小张","老师，一会我把报告给您","9月22日 10:11", R.drawable.ic_test_friend4);
    firendList.add(e);
    Firend f = new Firend("小陈","老师你好","9月21日 16:28", R.drawable.ic_test_friend5);
    firendList.add(f);
    Firend g = new Firend("赵老师","老王，早上一起去吃个早餐","9月20日 06:24", R.drawable.ic_test_friend6);
    firendList.add(g);
    Firend h = new Firend("于老师","今天检查学生宿舍","9月19日 09:50", R.drawable.ic_test_friend7);
    firendList.add(h);
    Firend i = new Firend("周老师","早上一起跑个步吧","9月19日 06:55", R.drawable.ic_test_friend8);
    firendList.add(i);
    Firend j = new Firend("小武","老师，一会把东西发给你","9月18日 15:13", R.drawable.ic_test_friend9);
    firendList.add(j);
    Firend k = new Firend("丛老师","中午一起开个会","9月17日 09:23", R.drawable.ic_test_friend10);
    firendList.add(k);
    Firend l = new Firend("田老师","老王，最近干什么呢","9月16日 10:11", R.drawable.ic_test_friend11);
    firendList.add(l);

}
}
