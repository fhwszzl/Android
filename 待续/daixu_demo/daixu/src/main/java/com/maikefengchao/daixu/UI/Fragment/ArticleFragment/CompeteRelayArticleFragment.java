package com.maikefengchao.daixu.UI.Fragment.ArticleFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Fragment.BaseFragment;

/**
 * Created by PC on 2016/6/26.
 */
public class CompeteRelayArticleFragment extends BaseFragment {

    private CompeteRelayArticleFragment() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_compete_relay_article);
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
    /**
     * 可调用的方法
     *
     * */
    public static CompeteRelayArticleFragment newInstance(){
        Bundle args = new Bundle();
        CompeteRelayArticleFragment fragment = new CompeteRelayArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
