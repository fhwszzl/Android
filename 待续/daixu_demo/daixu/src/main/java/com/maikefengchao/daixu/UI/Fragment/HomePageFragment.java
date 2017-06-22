package com.maikefengchao.daixu.UI.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.maikefengchao.daixu.Adatper.ArticleBriefAdapter;
import com.maikefengchao.daixu.Adatper.BaseRecyclerAdapter;
import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Activity.ArticleActivity;
import com.maikefengchao.daixu.UI.Activity.MainActivity;
import com.maikefengchao.daixu.View.RefreshView;

import java.util.ArrayList;

/**
 * Created by PC on 2016/5/14.
 */
public class HomePageFragment extends BaseRecyclerFragment {
    public static final String FRAGMENT_TAG = "HomePageFragment";
    private PullToRefreshRecyclerView mRecyclerView;
    //置顶刷新的控件
    private RefreshView mRefreshView;
    private LinearLayout mLinearTitle;

    private ArticleBriefAdapter mAdapter;
    private OnMenuAnimListener mListener;

    private Boolean isShow = true;
    //最小滑动距离
    private int mTouchSlop;

    @Override
    protected void initCommonView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_homepage);

        mTouchSlop = ViewConfiguration.get(mContext).getScaledTouchSlop();
        mRefreshView = getViewById(R.id.refreshView);
        initTitle();

    }
    /**
     *初始化头部导航栏
     * */
    private void initTitle(){
        mLinearTitle = getViewById(R.id.homePage_linear_title);
    }

    @Override
    protected PullToRefreshRecyclerView initRecyclerView() {
        mRecyclerView = getViewById(R.id.recyclerView_refresh);
        //是否拥有空视图
        hasEmptyView(false);
        testAdapter();
//        setRecyclerHeader();
        return mRecyclerView;
    }



    @Override
    protected void initListener() {

        /**
         * 禁止该功能，不让顶栏隐藏
         */
        //显示与隐藏菜单栏的逻辑
        mRecyclerView.addOnScrollListener(new PullToRefreshRecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //隐藏菜单栏
                if (Math.abs(dy) > mTouchSlop){
                    if (dy < 0){
                        if (!isShow){
                            //上划 显示
//                            ObjectAnimator topAnimation = ObjectAnimator.ofFloat(mLinearTitle,"translationY",0);
//                            topAnimation.start();
                            mListener.menuAnim(MainActivity.MENU_REFRESH);
                            isShow = true;
                        }

                    }
                    else {
                        if (isShow){
//                            ObjectAnimator topAnimation = ObjectAnimator.ofFloat(mLinearTitle,"translationY",-mLinearTitle.getHeight());
//                            topAnimation.start();
                            //下拉 隐藏
                            mListener.menuAnim(MainActivity.MENU_LOADING);
                            isShow = false;
                        }
                    }
                }
            }

            @Override
            public void onScroll(RecyclerView recyclerView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        /**
         * 配置监听器，当点击ListView的item之后触发
         * */
        mAdapter.setOnRecyclerViewItemListener(new BaseRecyclerAdapter.OnRecyclerViewItemListener<String>() {
            @Override
            public void onItemClick(View view, String data) {
                Intent intent = new Intent(getActivity(),ArticleActivity.class);
                intent.putExtra(ArticleActivity.EXTRA_FRAGMENT_TYPE,ArticleActivity.FRAGMENT_FREE_ARTICLE);
                startActivity(intent);
            }
        });

        //置顶刷新的监听
        mRefreshView.setOnRefreshStartListener(new RefreshView.OnRefreshStartListener() {
            @Override
            public void refreshStart() {
                //先回到顶部再刷新
                mRecyclerView.scrollToPosition(0);
                mRecyclerView.setRefreshing(true);
                /**
                 * 测试数据修改为网络的逻辑
                 */
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
                        //关闭刷新动画
                        mRecyclerView.setRefreshing(false);
                        mRecyclerView.setOnRefreshComplete();
                        mRecyclerView.onFinishLoading(true, false);
                        mRefreshView.refreshAnimStop();
                    }
                }.execute();
            }
        });

    }

    /**
     * 逻辑执行区
     * */
    @Override
    protected void processRecyclerLogic() {
    }


    /**
     * 当第一次进入的时候，自动加载的方法
     */
    @Override
    protected void firstRecyclerLoadData() {
        //调用刷新的动画（头部的刷新控件显示）,根据SwipeLayout无法自动刷新，只能够手动构造。
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.setRefreshing(true);
            }
        });


        //手动刷新数据
        if(mHttpConnection.IsNetAvailable(mContext)){
            /**
             *替换为网络的逻辑
             */
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
                    mRecyclerView.setRefreshing(false);
                    mRecyclerView.setOnRefreshComplete();
                    mRecyclerView.onFinishLoading(true, false);
                }
            }.execute();
        }
        else {
            //从数据库中获取
        }
    }

    /**
     * 下滑刷新
     *
     */
    @Override
    protected void refreshData() {
        /**
        * 测试，应该替换为网络的逻辑
         */
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

    /**
     * 上划加载更多
     */
    @Override
    protected void loadMoreData() {
        //当正在加载的时候不允许使用刷新置顶功能
        mRefreshView.setCanRefresh(false);
        /**
         * 测试，应该替换为网络逻辑和数据库逻辑
         */
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
                //加载完成时允许刷新
                mRefreshView.setCanRefresh(true);
            }
        }.execute();
    }



    /**
     * 该方法没用
     */
    @Override
    public void emptyViewReloadData() {

    }
    /**
     *
     */
    private void testAdapter(){
        //格式
        mAdapter = new ArticleBriefAdapter(mContext);
        mRecyclerView.setAdapter(mAdapter);
    }
    /**
     *设置ListView的头部，防止头部导航栏遮挡
     *
     *    禁止该功能，留出recyclerview的Header
     * */
//    private void setRecyclerHeader(){
//        View header = new View(getActivity());
//        header.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ScreenUtils.dp2px(60)));
//        mRecyclerView.addHeaderView(header);
//    }

    /**
     * 当HomePage活动的时候隐藏菜单栏
     */
    public interface OnMenuAnimListener {
        void menuAnim(int flag);
    }

    //利用监听器与Activity交互
    public void setHomePageFragmentListener(OnMenuAnimListener listener){
        mListener = listener;
    }
}
