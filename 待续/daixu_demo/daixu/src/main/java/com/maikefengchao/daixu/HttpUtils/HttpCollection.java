package com.maikefengchao.daixu.HttpUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by PC on 2016/5/27.
 */
public class HttpCollection  {
    private static final String BASE_URL = "http://ip.taobao.com";
    private static ApiService  mApi;
    private static volatile HttpCollection mConnection;

    private HttpCollection(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mApi = retrofit.create(ApiService.class);
    }

    //使用单例，并二次判断
    public static synchronized HttpCollection getInstance(){
        if (mConnection == null){
            synchronized (HttpCollection.class){
                return new HttpCollection();
            }
        }
        else {
            return mConnection;
        }
    }

    public ApiService getApiService(){
        return mApi;
    }

    //数据获取的示例
    public void getData(Subscriber<Object> subscriber){
        mApi.getIpInfo("21.22.11.33")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

   //上传图片的示例
    public void postImg(String filePath){
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        Call<String> call = mApi.uploadImage("name",requestBody);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Response<String> response) {

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }

    /**
     * 当前是否有网络连接
     * @return
     */
    public boolean IsNetAvailable(Context context){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        return (info != null && info.isConnected());
    }
}
