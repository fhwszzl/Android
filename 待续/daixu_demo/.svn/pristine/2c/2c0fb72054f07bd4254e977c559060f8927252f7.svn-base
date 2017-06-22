package com.maikefengchao.daixu.Adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.maikefengchao.daixu.Bean.Topic;
import com.maikefengchao.daixu.R;

import java.util.ArrayList;

/**
 * Created by PC on 2016/6/26.
 * 发现页：推送的话题或者广告
 */
public class TopicAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Topic> mTopicItems;
    public TopicAdapter(Context context) {
        mContext = context;
        mTopicItems = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mTopicItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mTopicItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_topic,parent,false);
            //初始化Holder
        }
        holder = (ViewHolder) convertView.getTag();
        return convertView;
    }

    private class ViewHolder{
        private SimpleDraweeView bgIconUrl;
        private TextView topicTitle;
        private TextView topicType;
        private TextView readCount;
    }
}
