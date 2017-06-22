package com.chinark.apppickimagev3.TakePhoto;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;

/**
 * 拍照的工具
 * 问题：可能拍照后的图片从相册中找不到
 * Created by PC on 2016/6/3.
 */
public class Photo {
    private static File parentFile = null;
    private static Uri mFileUri;

    private static Uri createFileUri(){
        if (parentFile == null){
            parentFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        }
        File file = new File(parentFile,System.currentTimeMillis()+".png");
        return Uri.fromFile(file);
    }
    //打开相机拍照
    public static Intent takePhoto(){
        mFileUri = createFileUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mFileUri);
        return intent;
    }
    //获取uri
    public static Uri getFileUri() {
        return mFileUri;
    }

    //将Uri转化为Bitmap
    private Bitmap decodeUriAsBitmap(Uri uri){
        Bitmap photo = null;
        Uri photoUri = uri;
        if (photoUri != null) {
            photo = BitmapFactory.decodeFile(photoUri.getPath());
        }
        if (photo == null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }
        return photo;
    }
}
