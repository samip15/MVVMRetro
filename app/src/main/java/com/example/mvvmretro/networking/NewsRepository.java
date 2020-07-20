package com.example.mvvmretro.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmretro.Model.NewsResponce;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;
    public static NewsRepository getInstance(){
        if (newsRepository==null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }
    private NewsApi newsApi;
    public NewsRepository(){
       newsApi = RetrofitService.createService(NewsApi.class);
    }

    /**
     * Helps to get our news response as mutable live data so that we can set or post value to our ui
     * @param source:news soure
     * @param apiKey:
     * @return:get mutable data
     */
    public MutableLiveData<NewsResponce> getNews(String source,String apiKey){
        // initialize
        final MutableLiveData<NewsResponce> newsData  = new MutableLiveData<>();
        // api call get news list
        newsApi.getNewsList(source,apiKey).enqueue(new Callback<NewsResponce>() {
            @Override
            public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {
                if (response.isSuccessful()){
                    // setting our response directly as it get automatically
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewsResponce> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
