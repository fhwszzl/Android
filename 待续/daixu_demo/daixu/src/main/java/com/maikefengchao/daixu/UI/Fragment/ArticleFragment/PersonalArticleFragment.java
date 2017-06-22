package com.maikefengchao.daixu.UI.Fragment.ArticleFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Fragment.BaseFragment;

/**
 * Created by PC on 2016/6/26.
 */
public class PersonalArticleFragment extends BaseFragment {
    private PersonalArticleFragment() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_personal_article);
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
    public static PersonalArticleFragment newInstance(){
        Bundle args = new Bundle();
        PersonalArticleFragment fragment = new PersonalArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
