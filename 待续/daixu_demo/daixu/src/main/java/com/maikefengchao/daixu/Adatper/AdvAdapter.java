package com.maikefengchao.daixu.Adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maikefengchao.daixu.Bean.Advertisement;
import com.maikefengchao.daixu.R;

/**
 * Created by PC on 2016/7/3.
 */
public class AdvAdapter extends BaseRecyclerAdapter<String> {

    public AdvAdapter(Context context) {
        super(context);
    }

    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup parent,int viewType) {
        View v = inflater.inflate(R.layout.fragment_listview_article,parent,false);
        return v;
    }

    @Override
    protected RecyclerView.ViewHolder getHolder(View view) {
        return new AdvViewHolder(view);
    }
    /**
     * 作用 1、如果绑定了监听器，传送给监听器的数据
     *     2、设置显示的内容
     *
     */
    @Override
    protected void onBindHolder(RecyclerView.ViewHolder holder, int position) {
        String adv = getArrayList().get(position);
        ((AdvViewHolder)holder).tvContent.setText(adv);
        //传送给监听器的数据
        holder.itemView.setTag(adv);
    }

    //ViewHolder
    class AdvViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        public AdvViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
