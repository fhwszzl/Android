package com.maikefengchao.daixu.UI.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.Utils.DividerItemDecoration;
import com.maikefengchao.daixu.View.DemoLoadMoreView;

/**
 * Created by PC on 2016/7/9.
 */
public abstract class BaseRecyclerFragment extends BaseFragment implements PullToRefreshRecyclerView.PagingableListener,PullToRefreshRecyclerView.OnRefreshListener {
    private PullToRefreshRecyclerView mRecyclerView;
    private ProgressBar mPbLoading;
    private Button mBtnReLoad;
    private DemoLoadMoreView mMoreView;
    private boolean isHasEmpty = false;

    @Override
    protected void initView(Bundle savedInstanceState) {
        initCommonView(savedInstanceState);
        mRecyclerView = initRecyclerView();
        decorateRecyclerView();
    }

    @Override
    protected void setListener() {
        //刷新的监听
        mRecyclerView.setPagingableListener(this);
        mRecyclerView.setOnRefreshListener(this);
        initListener();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        processRecyclerLogic();
    }

    @Override
    protected void firstLoadData() {
        firstRecyclerLoadData();
    }

    @Override
    public void onRefresh() {
        refreshData();
    }

    @Override
    public void onLoadMoreItems() {
        loadMoreData();
    }

    private void decorateRecyclerView(){
        initFooter();
        initDivision();

        //设置是否允许上拉刷新
        mRecyclerView.setSwipeEnable(true);
        //当刷新完成后一定要加这句话,是否允许刷新
        mRecyclerView.onFinishLoading(true,false);
    }

    private void initFooter(){
        //设置底部的加载动画
        mMoreView = new DemoLoadMoreView(mContext, mRecyclerView.getRecyclerView());
        mMoreView.setLoadmoreString("加载更多");
        mMoreView.setLoadMorePadding(100);
        mRecyclerView.setLoadMoreFooter(mMoreView);
    }

    private void initDivision(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        //设置View之间的divide
        mRecyclerView.getRecyclerView().addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
    }
    private void initEmptyView(){
        View emptyView = View.inflate(getActivity(), R.layout.view_empty,null);
        mPbLoading = (ProgressBar) emptyView.findViewById(R.id.empty_pb);
        mBtnReLoad = (Button)emptyView.findViewById(R.id.empty_btn_reload);

        mBtnReLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //视图切换
                showEmptyViewLoading();
                //重新加载
                emptyViewReloadData();
            }
        });
        mRecyclerView.setEmptyView(emptyView);
    }

    private void showEmptyViewLoading(){
        mPbLoading.setVisibility(View.VISIBLE);
        mBtnReLoad.setVisibility(View.GONE);
    }

    /**************************内部方法end**************************/

    /************************继承的接口**************************/

    /**
     * 初始化xml配置
     * */
    protected abstract void initCommonView(Bundle savedInstanceState);
    /**
     * 初始化RecyclerVIew
     * */
    protected abstract PullToRefreshRecyclerView initRecyclerView();
    /**
     * 设置监听器
     * */
    protected abstract void initListener();
    /**
     * 逻辑实现区
     */
    protected abstract void processRecyclerLogic();
    /**
     * 首次进入，自动刷新
     * */
    protected abstract void firstRecyclerLoadData();
    /**
     * 下滑刷新
     * */
    protected abstract void refreshData();
    /**
     * 上拉加载
     * */
    protected abstract void loadMoreData();



    /**
     * 当加载失败时候重新加载
     */
    public abstract void emptyViewReloadData();

    public void showEmptyViewLoadFalse(){
        mPbLoading.setVisibility(View.GONE);
        mBtnReLoad.setVisibility(View.VISIBLE);
    }

    /*********************公共方法******************/
    /**
     * 判断是否拥有空视图
     */
    public void hasEmptyView(boolean empty){
        isHasEmpty = empty;
    }
    /**
     * 设置上拉加载时底部动画的高度
     */
    public void setFooterHeight(int height){
        mMoreView.setLoadMorePadding(height);
    }
}
