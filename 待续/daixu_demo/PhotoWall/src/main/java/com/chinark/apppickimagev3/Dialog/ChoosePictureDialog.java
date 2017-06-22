package com.chinark.apppickimagev3.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chinark.apppickimagev3.R;
import com.chinark.apppickimagev3.TakePhoto.Photo;
import com.chinark.apppickimagev3.ui.PhotoAlbumActivity;
import com.chinark.apppickimagev3.ui.PhotoWallActivity;

import java.io.Externalizable;
import java.io.File;

/**
 * Created by PC on 2016/6/23.
 */
public class ChoosePictureDialog extends Dialog{
    public static final int RESULT_DIALOG_TAKE_PHOTO = 0X11;
    private Button mBtnTakePhoto;
    private Button mBtnChoosePicture;
    private Context mContext;
    public ChoosePictureDialog(Context context) {
        super(context);
        mContext = context;
    }

    public ChoosePictureDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public ChoosePictureDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_picture);
        mBtnTakePhoto =(Button)findViewById(R.id.choosePicture_btn_takePhoto);
        mBtnChoosePicture = (Button)findViewById(R.id.choosePicture_btn_choosePicture);
        initOnClick();
    }

    private void initOnClick(){
        mBtnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)mContext).startActivityForResult(Photo.takePhoto(),RESULT_DIALOG_TAKE_PHOTO);
                dismiss();
            }
        });
        mBtnChoosePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果有必要刻意将点击事件拿出来
                Intent intent = new Intent(getContext(), PhotoWallActivity.class);
                intent.putExtra(PhotoWallActivity.EXTRA_IS_CROP,true);
                intent.putExtra(PhotoWallActivity.EXTRA_LAUNCH_CLASS,mContext.getClass().getName());
                getContext().startActivity(intent);
                dismiss();
            }
        });
    }
}
