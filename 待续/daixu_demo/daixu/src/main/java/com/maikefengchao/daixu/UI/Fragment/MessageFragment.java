package com.maikefengchao.daixu.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Activity.ArticleActivity;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by PC on 2016/5/14.
 */
public class MessageFragment extends BaseFragment{
    public static final String FRAGMENT_TAG = "MessageFragment";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_message);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void firstLoadData() {

    }
}
