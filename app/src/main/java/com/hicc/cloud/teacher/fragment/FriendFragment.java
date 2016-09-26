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
    Firend a = new Firend("A", R.drawable.ic_launcher);
    firendList.add(a);
    Firend b = new Firend("B", R.drawable.ic_launcher);
    firendList.add(b);
    Firend c = new Firend("C", R.drawable.ic_launcher);
    firendList.add(c);
    Firend d = new Firend("D", R.drawable.ic_launcher);
    firendList.add(d);
    Firend e = new Firend("E", R.drawable.ic_launcher);
    firendList.add(e);
    Firend f = new Firend("F", R.drawable.ic_launcher);
    firendList.add(f);
    Firend g = new Firend("G", R.drawable.ic_launcher);
    firendList.add(g);
    Firend h = new Firend("H", R.drawable.ic_launcher);
    firendList.add(h);
    Firend i = new Firend("I", R.drawable.ic_launcher);
    firendList.add(i);
    Firend j = new Firend("J", R.drawable.ic_launcher);
    firendList.add(j);
    Firend k = new Firend("K", R.drawable.ic_launcher);
    firendList.add(k);
    Firend l = new Firend("L", R.drawable.ic_launcher);
    firendList.add(l);

}
}
