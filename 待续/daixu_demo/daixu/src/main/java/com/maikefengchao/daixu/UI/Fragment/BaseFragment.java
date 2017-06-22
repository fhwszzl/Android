package com.maikefengchao.daixu.UI.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.maikefengchao.daixu.App;
import com.maikefengchao.daixu.HttpUtils.ApiService;
import com.maikefengchao.daixu.HttpUtils.HttpCollection;
import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.Utils.ToastUtil;

/**
 * Created by PC on 2016/6/1.
 */
public abstract class BaseFragment extends Fragment {
    protected String TAG;
    protected App mApp;
    protected Context mContext;
    protected View mRootView;
    protected HttpCollection mHttpConnection;
    protected ApiService mApiSerivce;
    private View mContentView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //RTTI机制获取Activity的名字
        TAG = activity.getClass().getSimpleName();
        //获取全局变量
        mApp = App.getInstance();
        //获取网络连接类
        mHttpConnection = mApp.getHttpCollection();
        mApiSerivce = mHttpConnection.getApiService();
        mContext = activity;
        mRootView = activity.getWindow().getDecorView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 防止Fragment多次切换时调用onCreateView重新加载View
         * 减少了xml多次调用的过程
         */
        if (mContentView == null){
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
        }
        else {
            /**
             * 缓存的rootView需要判断是否已经被加过parent，
             * 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
             * 因为mContentView已经在里面了，所以需要删掉重新加载。
             */
            ViewGroup parent = (ViewGroup)mContentView.getParent();
            if (parent != null){
                parent.removeView(mContentView);
            }
        }
        firstLoadData();
        return mContentView;
    }

    /**
     * 初始化View控件
     */
    protected abstract void initView(Bundle savedInstanceState);
    /**
     * 给View控件添加事件监听器
     */
    protected abstract void setListener();
    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected abstract void processLogic(Bundle savedInstanceState);
    /**
     *首次加载数据
     */
    protected abstract void firstLoadData();
    /**
     * 设置Fragment的xml文件
     * @param layoutId   layout文件的id
     */
    protected void setContentView(@LayoutRes int layoutId){
        mContentView = LayoutInflater.from(mContext).inflate(layoutId,null);
    }
    /**
     * 查找View，简化从xml中查到id的过程
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View>VT getViewById(@IdRes int id){
        return (VT) mContentView.findViewById(id);
    }

    /**
     * 展示Toast
     * @param text
     */
    protected void showToast(String text) {
        ToastUtil.show(text);
    }
}
