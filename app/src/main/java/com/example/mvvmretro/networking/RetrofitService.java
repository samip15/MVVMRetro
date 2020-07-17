package com.example.mvvmretro.networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    // service
    public static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
