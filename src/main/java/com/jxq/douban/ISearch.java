package com.jxq.douban;

import java.util.List;

import com.jxq.douban.domain.KeywordResponse;
import com.jxq.douban.domain.MovieResponseVO;
import com.jxq.douban.domain.WHResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @Auther: jx
 * @Date: 2018/7/13 17:44
 * @Description: 豆瓣查询电影分类接口
 */
public interface ISearch {
    @GET("j/search_tags")
    Call<MovieResponseVO> searchTags(@Query("type") String type, @Query("source") String source);
    
    @GET("s")
    Call<ResponseBody> searchKeywords(@Query("wd") String wd);
    
    @FormUrlEncoded
    @POST("wenhaiFt/login/mod1/login")
    Call<ResponseBody> login(@Field("username") String username,@Field("password") String password);
    
    
    @GET("php_cgi/news/php/varcache_getnews.php")
    Call<ResponseBody> sousuo(@Query("id") String id, @Query("page") String page,@Query("plat") String plat,@Query("version") String version);
    
    @FormUrlEncoded
//    @Headers("Cookie: JSESSIONID=16E44E4ECD22DFC49D4FA08288755CF0")
    @POST("wenhaiFt/search_al/mod2/dataSourceCount")
    Call<ResponseBody> wenhaiCount(@Field("params[]")  List<String> params,@Field("sid") String sid);
    
    @GET("common_al/common/getNewsColumnData")
    Call<ResponseBody> rmtList(@Query("page") String page, @Query("size") String size,@Query("columnId") String columnId);

    @GET("common_al/common/getNewsDetailData")
    Call<ResponseBody> rmtContent(@Query("type") String type, @Query("data") String data);
}
