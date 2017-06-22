package com.maikefengchao.daixu.HttpUtils;

import com.maikefengchao.daixu.Bean.Advertisement;
import com.maikefengchao.daixu.Bean.ArticleBrief;
import com.maikefengchao.daixu.Bean.PersonInfoBrief;
import com.maikefengchao.daixu.Bean.Topic;
import com.maikefengchao.daixu.HttpUtils.Model.GetIpResponse;
import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by PC on 2016/5/27.
 */
public interface ApiService {
    @GET("service/php")
    Observable<GetIpResponse> getIpInfo(@Query("ip") String ip);

    @GET("获取首页信息的地址")
    Observable<ArticleBrief> getArticleBriefs();
    @GET("获取发现页信息的地址")
    Observable<Topic> getTopics();
    @GET("发现页的广告轮播图")
    Observable<Advertisement> getAdertisements();
    @GET("个人信息界面")
    Observable<PersonInfoBrief> getPersonInfoBrief();

    /**
     * 上传一张图片
     * @param description
     * @param imgs
     * @return
     */
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description,
                             @Part("file\"; filename=\"image.png\"") RequestBody imgs);
}
