package com.maikefengchao.daixu.UI.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chinark.apppickimagev3.Dialog.ChoosePictureDialog;
import com.chinark.apppickimagev3.Dialog.ChoosePictureFragment;
import com.chinark.apppickimagev3.TakePhoto.Photo;
import com.chinark.apppickimagev3.adapter.MainGVAdapter;
import com.chinark.apppickimagev3.ui.CropPictureActivity;
import com.chinark.apppickimagev3.ui.PhotoWallActivity;
import com.maikefengchao.daixu.R;

import java.util.ArrayList;

/**
 * Created by PC on 2016/5/14.
 * 个人中心
 */
public class PersonalCenterFragment extends BaseFragment {
    public static final String FRAGMENT_TAG = "PersonalCenterFragment";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_personalcenter);
//        mBtnShowPhoto = getViewById(R.id.personal_btn_showPhoto);
//        mChoosePictureFragment = new ChoosePictureFragment();
    }

    @Override
    protected void setListener() {
//        mBtnShowPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FragmentManager manager = getFragmentManager();
//                mChoosePictureFragment.show(manager,ChoosePictureFragment.TAG_NAME);
//            }
//        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void firstLoadData() {

    }
}
