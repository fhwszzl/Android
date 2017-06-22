package com.maikefengchao.daixu.TakePhoto;

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
    private static Uri mFileUri;
    private static File parentFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);

    private static void createFileUri(){
        File file = new File(parentFile,System.currentTimeMillis()+".png");
        mFileUri = Uri.fromFile(file);
    }
    //打开相机拍照
    public static Intent takePhoto(){
        createFileUri();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,mFileUri);
        return intent;
    }
    //打开相册
    public static Intent choosePicture(int outputX, int outputY){
        createFileUri();
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(mFileUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        return intent;
    }
    //剪切照片, 注：必须配合打开相机使用
    public static Intent cropImageUri(int outputX, int outputY){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(mFileUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 2);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
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
