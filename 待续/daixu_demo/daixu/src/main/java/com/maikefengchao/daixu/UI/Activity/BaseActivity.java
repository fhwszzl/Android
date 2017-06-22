package com.maikefengchao.daixu.UI.Activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maikefengchao.daixu.App;
import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.Utils.ToastUtil;

/**
 * Created by PC on 2016/6/1.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    protected App mApp;
    protected String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mApp = App.getInstance();
        initView(savedInstanceState);
        setListener();
        processLogic(savedInstanceState);
    }

    //初始化View的逻辑写在这里
    protected abstract void initView(Bundle saveInstanceState);
    //注册监听的逻辑写这里
    protected abstract void setListener();
    //实现的逻辑写这里
    protected abstract void processLogic(Bundle saveInstanceState);
    //实现单击事件的逻辑
    @Override
    public void onClick(View v) {
    }

    protected <VT extends View> VT getViewById(@IdRes int id){
        return (VT)findViewById(id);
    }
    //显示Toast的功能
    protected void showToast(String text) {
        ToastUtil.show(text);
    }
}
