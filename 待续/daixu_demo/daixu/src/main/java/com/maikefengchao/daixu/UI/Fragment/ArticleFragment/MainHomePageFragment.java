package com.maikefengchao.daixu.UI.Fragment.ArticleFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.maikefengchao.daixu.R;
import com.maikefengchao.daixu.UI.Fragment.BaseFragment;
import com.maikefengchao.daixu.UI.Fragment.HomePageFragment;
import com.maikefengchao.daixu.UI.Fragment.HomepageChildGsxFragment;

import java.util.ArrayList;

/**
 * Created by ALUEX on 2016/11/27.
 */

public class MainHomePageFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = "MainHomePageFragment";
    private ViewPager mViewPager;
    private TextView mTVcjjl, mTVgsx;

    ArrayList<TextView> textlist = new ArrayList<TextView>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_main_homepage);

        final FragmentManager fragmentManager = getChildFragmentManager();
        //初始化
        mViewPager = getViewById(R.id.main_viewpager);
        mTVcjjl = getViewById(R.id.homepage_tab_cjjl);
        mTVgsx = getViewById(R.id.homepage_tab_gsx);
        textlist.add(mTVcjjl);
        textlist.add(mTVgsx);
        //配置viewpager适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {

            @Override
            public int getCount() {
                return 2;
            }
            //根据索引值获取fragment
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new HomePageFragment();
                switch (position){
                    case 0:
                        fragment = new HomePageFragment();
                        break;
                    case 1:
                        fragment = new HomepageChildGsxFragment();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

        });
    }

    @Override
    protected void setListener() {
        //Tab字体变色
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            //被选中
            @Override
            public void onPageSelected(int position) {
                TextView textView = textlist.get(position);
                textView.setTextColor(Color.RED);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void firstLoadData() {

    }
}
