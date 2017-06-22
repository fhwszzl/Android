package com.chinark.apppickimagev3.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chinark.apppickimagev3.R;
import com.oginotihiro.cropview.Crop;
import com.oginotihiro.cropview.CropUtil;
import com.oginotihiro.cropview.CropView;

import java.security.cert.Certificate;

/**
 * Created by PC on 2016/6/23.
 */
public class CropPictureActivity extends Activity {
    public static final String EXTRA_FILE_URI = "cropPicture_fileUri";
    private CropView mCropView;
    private Button mBtnSure;
    private Button mBtnCancel;

    private Uri mFileUri = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_picture);
        mCropView = (CropView) findViewById(R.id.cropPicture_cv);
        mBtnSure = (Button)findViewById(R.id.cropPicture_btn_sure);
        mBtnCancel = (Button)findViewById(R.id.cropPicture_btn_cancel);
        initOnClick();

        //逻辑
        Intent intent = getIntent();
        mFileUri = (Uri) intent.getExtras().get(EXTRA_FILE_URI);
        mCropView.of(mFileUri)
                .withAspect(2,1)
                .withOutputSize(500,500)
                .initialize(this);
    }

    private void initOnClick(){
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //确定提交照片
                finish();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消提交照片
                finish();
            }
        });
    }
}
