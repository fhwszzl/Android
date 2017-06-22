package com.maikefengchao.daixu;

import android.app.Application;

import com.chinark.apppickimagev3.utils.ScreenUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.maikefengchao.daixu.HttpUtils.HttpCollection;

public class App extends Application {
    /**
     * 全局的Context
     * 测试时间：18:58
     */
    private static App sInstance;
    private static HttpCollection mConnection;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mConnection = HttpCollection.getInstance();
        Fresco.initialize(this);
    }

    public static App getInstance() {
        return sInstance;
    }

    public HttpCollection getHttpCollection(){
        return mConnection;
    }
}