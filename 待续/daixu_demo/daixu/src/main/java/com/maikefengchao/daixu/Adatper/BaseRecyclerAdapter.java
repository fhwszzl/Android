package com.maikefengchao.daixu.Adatper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maikefengchao.daixu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by PC on 2016/7/3.
 */
public abstract class BaseRecyclerAdapter <T> extends RecyclerView.Adapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<T> mArrayList;
    private OnRecyclerViewItemListener mListener;

    public BaseRecyclerAdapter(Context context){
        mContext = context;
        mArrayList = new ArrayList<>();
    }
    /**
     * 创建View的地方
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = onCreateView(LayoutInflater.from(mContext),parent,viewType);
        v.setOnClickListener(this);
        return getHolder(v);
    }
    /**
     * 当item显示的时候，回调该方法。所以主要用来做对item做逻辑处理
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    /**
     * 获取View的布局
     */
    protected abstract View onCreateView(LayoutInflater inflater,ViewGroup parent,int viewType);
    /**
     * 获取自定义的ViewHolder
     *
     */
    protected abstract RecyclerView.ViewHolder getHolder(View view);
    /**
     * 作用 1、如果绑定了监听器，传送给监听器的数据
     *     2、设置View显示的内容
     */
    protected abstract void onBindHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public void onClick(View v) {
        if (mListener != null){
            mListener.onItemClick(v,v.getTag());
        }
    }

    //监听器
    public interface OnRecyclerViewItemListener <T> {
        void onItemClick(View view,T data);
    }


    /**
     * 公共的方法，可调用
     */

    /**
     * 添加一个数据到ListVIew中
     * */
    public void addItem(T item){
        mArrayList.add(item);
    }

    /**
     * 添加一组数据到ListVIew中，并刷新
     * */
    public void addItems(ArrayList<T> items){
        mArrayList.addAll(items);
        notifyDataSetChanged();
    }

    /**
     *作用：删除所有数据，重新加载
     */
    public void refreshItems(ArrayList<T> stringList){
        mArrayList.clear();
        mArrayList.addAll(stringList);
        notifyDataSetChanged();
    }

    //获取容器
    public ArrayList<T> getArrayList() {
        return mArrayList;
    }

    //设置监听器
    public void setOnRecyclerViewItemListener(OnRecyclerViewItemListener<T> listener) {
        this.mListener = listener;
    }

    public OnRecyclerViewItemListener getOnRecyclerViewItemListener() {
        return mListener;
    }
}
