package com.maikefengchao.daixu.UI.Fragment.ArticleFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.maikefengchao.daixu.Adatper.ArticleAdapter;
import com.maikefengchao.daixu.Adatper.ArticleBriefAdapter;
import com.maikefengchao.daixu.Adatper.BaseRecyclerAdapter;
import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Activity.ShowPictureActivity;
import com.maikefengchao.daixu.UI.Fragment.BaseFragment;
import com.maikefengchao.daixu.UI.Fragment.BaseRecyclerFragment;
import com.maikefengchao.daixu.Utils.DividerItemDecoration;
import com.maikefengchao.daixu.View.DemoLoadMoreView;
import com.maikefengchao.relybox.OnOperationListener;
import com.maikefengchao.relybox.bean.Emojicon;
import com.maikefengchao.relybox.bean.Faceicon;
import com.maikefengchao.relybox.emoji.DisplayRules;
import com.maikefengchao.relybox.widget.FreeWriteKeyboard;
import com.maikefengchao.relybox.widget.KJChatKeyboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/6/26.
 */
public class FreeArticleFragment extends BaseFragment implements PullToRefreshRecyclerView.OnRefreshListener,PullToRefreshRecyclerView.PagingableListener{
    private static final String TAG = "FreeArticleFragment";
    private PullToRefreshRecyclerView mRecyclerView;
    private FreeWriteKeyboard mKeyboard;
    private ArticleAdapter mAdapter;
    private ProgressBar mPbLoading;
    private Button mBtnReLoad;
    //不允许通过new创建Fragment
    private FreeArticleFragment() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_free_article);
        mKeyboard = getViewById(R.id.keyboard);
        mRecyclerView = getViewById(R.id.recyclerView_refresh);
        initRecycler();
        initMessageInputToolBox();
    }

    @Override
    protected void setListener() {
        //刷新的监听
        mRecyclerView.setPagingableListener(this);
        mRecyclerView.setOnRefreshListener(this);
        //当滑动时，缩小键盘
        mRecyclerView.getRecyclerView().setOnTouchListener(getOnTouchListener());
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void firstLoadData() {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ArrayList<String> strings = new ArrayList<>();
                for(int i=0; i<20; ++i){
                    strings.add("我很强"+i);
                }
                mAdapter.refreshItems(strings);
                mRecyclerView.setOnRefreshComplete();
                mRecyclerView.onFinishLoading(true, false);
                mKeyboard.setVisibility(View.VISIBLE);
            }
        }.execute();
    }

    @Override
    public void onRefresh() {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ArrayList<String> strings = new ArrayList<>();
                for(int i=0; i<20; ++i){
                    strings.add("我很强"+i);
                }
                mAdapter.refreshItems(strings);
                mRecyclerView.setOnRefreshComplete();
                mRecyclerView.onFinishLoading(true, false);
            }
        }.execute();
    }

    @Override
    public void onLoadMoreItems() {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                ArrayList<String> strings = new ArrayList<>();
                for(int i=0; i<10; ++i){
                    strings.add("我很强"+i);
                }
                mAdapter.addItems(strings);
                mRecyclerView.onFinishLoading(true,false);
            }
        }.execute();
    }

    private void initMessageInputToolBox(){
        List<String> faceCagegory = new ArrayList<>();
        mKeyboard.setOnOperationListener(new OnOperationListener() {
            @Override
            public void send(String content) {
                //发送
            }

            @Override
            public void selectedFace(Faceicon content) {
                //没有外部表情包
            }

            @Override
            public void selectedEmoji(Emojicon content) {
                mKeyboard.getEditTextBox().append(content.getValue());
            }

            @Override
            public void selectedBackSpace(Emojicon back) {
                DisplayRules.backspace(mKeyboard.getEditTextBox());
            }

            @Override
            public void selectedFunction(int index) {
                //工具箱的点击事件
            }
        });
        //如果有外部的表情包，创建并初始化
        File faceList = new File("");
        if (faceList.isDirectory()) {
            File[] faceFolderArray = faceList.listFiles();
            for (File folder : faceFolderArray) {
                if (!folder.isHidden()) {
                    faceCagegory.add(folder.getAbsolutePath());
                }
            }
        }
        mKeyboard.setFaceData(faceCagegory);
    }

    private void initRecycler(){
        initEmptyView();
        initDivision();
        initFooter();
        //设置是否允许上拉刷新
        mRecyclerView.setSwipeEnable(true);
        //当刷新完成后一定要加这句话,是否允许刷新
        mRecyclerView.onFinishLoading(true,false);
        testAdapter();
    }

    private void initFooter(){
        //设置底部的加载动画
        DemoLoadMoreView loadMoreView = new DemoLoadMoreView(mContext, mRecyclerView.getRecyclerView());
        loadMoreView.setLoadmoreString("加载更多");
        loadMoreView.setLoadMorePadding(100);
        mRecyclerView.setLoadMoreFooter(loadMoreView);
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
    private void testAdapter(){
        //格式
        mAdapter = new ArticleAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void emptyViewReloadData(){

    }
    /**
     *
     * 若软键盘或表情键盘弹起，点击上端空白处应该隐藏输入法键盘
     *
     * @return 会隐藏输入法键盘的触摸事件监听器
     */
    private View.OnTouchListener getOnTouchListener() {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mKeyboard.hideLayout();
                mKeyboard.hideKeyboard(getActivity());
                return false;
            }
        };
    }

    private void showEmptyViewLoading(){
        mPbLoading.setVisibility(View.VISIBLE);
        mBtnReLoad.setVisibility(View.GONE);
    }

    /**
     * 可调用的方法
     *
     * */
    public static FreeArticleFragment newInstance(){
        Bundle args = new Bundle();
        FreeArticleFragment fragment = new FreeArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK && mKeyboard.isShow()) {
            mKeyboard.hideLayout();
            return  true;
        }
        return false;
    }

    public void setUpdateImage(ArrayList<String> filePaths){
        mKeyboard.addUpdateImage(filePaths);
    }

}
