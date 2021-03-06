package com.maikefengchao.daixu.Adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maikefengchao.daixu.R;

/**
 * Created by PC on 2016/6/1.
 */
public class ArticleBriefAdapter extends BaseRecyclerAdapter<String>{

    public ArticleBriefAdapter(Context context) {
        super(context);
    }
    /**
     * 载入该ListVIew的xml配置
     */
    @Override
    protected View onCreateView(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return inflater.inflate(R.layout.fragment_listview_article,parent,false);
    }

    /**
     * 载入自定义的ViewHolder
     */
    @Override
    protected RecyclerView.ViewHolder getHolder(View view) {
        return new ArticleBriefViewHolder(view);
    }

    /**
     * 当item显示的时候调用该方法，显示表示从不可见变为可见的时候调用
     */
    @Override
    protected void onBindHolder(RecyclerView.ViewHolder holder, int position) {
        String adv = getArrayList().get(position);
        ((ArticleBriefViewHolder)holder).tvContent.setText(adv);
        //传送给监听器的数据
        holder.itemView.setTag(adv);
    }


    class ArticleBriefViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;
        public ArticleBriefViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }
}
