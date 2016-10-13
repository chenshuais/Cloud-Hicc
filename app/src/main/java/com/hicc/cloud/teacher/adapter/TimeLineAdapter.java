package com.hicc.cloud.teacher.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hicc.cloud.R;
import com.hicc.cloud.teacher.bean.TimeLineModel;
import com.vipul.hp_hp.timelineview.TimelineView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13/026.
 */
public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimeLineModel> mFeedList;
    private Context mContext;

    public TimeLineAdapter(List<TimeLineModel> feedList) {
        mFeedList = feedList;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();

        View view;

        view = View.inflate(parent.getContext(), R.layout.item_timeline, null);

        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

        TimeLineModel timeLineModel = mFeedList.get(position);

        holder.name.setText(timeLineModel.getTime() +"  "+ timeLineModel.getScool());

    }

    @Override
    public int getItemCount() {
        return (mFeedList!=null? mFeedList.size():0);
    }

}
