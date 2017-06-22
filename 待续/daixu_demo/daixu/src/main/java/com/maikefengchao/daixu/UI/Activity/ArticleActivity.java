package com.maikefengchao.daixu.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.widget.BaseAdapter;

import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Fragment.ArticleFragment.CompeteRelayArticleFragment;
import com.maikefengchao.daixu.UI.Fragment.ArticleFragment.FreeArticleFragment;
import com.maikefengchao.daixu.UI.Fragment.ArticleFragment.PersonalArticleFragment;

import java.util.ArrayList;

/**
 * Created by PC on 2016/6/26.
 */
public class ArticleActivity extends BaseActivity {
    public static final String EXTRA_FRAGMENT_TYPE = "article_fragment_type";
    public static final int FRAGMENT_FREE_ARTICLE = 0;
    public static final int FRAGMENT_COMPETE_RELAY = 1;
    public static final int FRAGMENT_PERSONAL = 2;
    private Fragment currentFragment;
    private int mFragmentType;
    @Override
    protected void initView(Bundle saveInstanceState) {
        setContentView(R.layout.activity_article);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle saveInstanceState) {
        initData();
        createFragment();
    }

    private void initData(){
        Intent intent = getIntent();
        mFragmentType = intent.getIntExtra(EXTRA_FRAGMENT_TYPE,0);
    }

    private void createFragment(){
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = null;
        switch (mFragmentType){
            case 0:
                fragment = FreeArticleFragment.newInstance();
                break;
            case 1:
                fragment = CompeteRelayArticleFragment.newInstance();
                break;
            case 2:
                fragment = PersonalArticleFragment.newInstance();
                break;
            default:
                break;
        }
        manager.beginTransaction().add(R.id.article_frame,fragment,"ArticleFragment").commit();
        currentFragment = fragment;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(currentFragment instanceof FreeArticleFragment){
          boolean isTrue =  ((FreeArticleFragment) currentFragment).onKeyDown(keyCode,event);
            if (isTrue){
                return true;
            }
            else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int code = intent.getIntExtra("code", -1);
        if (code != 100){
            return;
        }
        ArrayList<String> paths = intent.getStringArrayListExtra("paths");
        if(currentFragment instanceof FreeArticleFragment){
            ((FreeArticleFragment) currentFragment).setUpdateImage(paths);
        }
    }
}
