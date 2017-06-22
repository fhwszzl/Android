package com.maikefengchao.daixu.UI.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.maikefengchao.daixu.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.truba.touchgallery.GalleryWidget.BasePagerAdapter;
import ru.truba.touchgallery.GalleryWidget.GalleryViewPager;
import ru.truba.touchgallery.GalleryWidget.UrlPagerAdapter;

/**
 * Created by PC on 2016/7/9.
 */
public class ShowPictureActivity extends AppCompatActivity {
    private GalleryViewPager mViewPager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_picture);
        mViewPager = (GalleryViewPager)findViewById(R.id.showPicture_gvp);
        String[] urls = {
                "http://cs407831.userapi.com/v407831207/18f6/jBaVZFDhXRA.jpg",
                "http://cs407831.userapi.com/v4078f31207/18fe/4Tz8av5Hlvo.jpg",
                "http://cs407831.userapi.com/v407831207/1906/oxoP6URjFtA.jpg",
                "http://cs407831.userapi.com/v407831207/190e/2Sz9A774hUc.jpg",
                "http://cs407831.userapi.com/v407831207/1916/Ua52RjnKqjk.jpg",
                "http://cs407831.userapi.com/v407831207/191e/QEQE83Ok0lQ.jpg"
        };
        List<String> items = new ArrayList<String>();
        Collections.addAll(items, urls);

        UrlPagerAdapter pagerAdapter = new UrlPagerAdapter(this, items);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setCurrentItem(2);
    }
}
