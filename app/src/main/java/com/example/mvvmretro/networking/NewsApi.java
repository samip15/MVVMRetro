package com.example.mvvmretro.networking;

import com.example.mvvmretro.Model.NewsResponce;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    @GET("everything")
    Call<NewsResponce> getNewsList(@Query("q") String searchBy, @Query("apiKey") String apiKey);
}
