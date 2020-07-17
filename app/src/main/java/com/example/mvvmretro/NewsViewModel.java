package com.example.mvvmretro;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmretro.Model.NewsResponce;
import com.example.mvvmretro.networking.NewsRepository;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<NewsResponce> mutableLiveData;
    private NewsRepository newsRepository;
    public void init(){
        if (mutableLiveData!=null){
            return;
        }
        newsRepository = new NewsRepository();
        mutableLiveData = newsRepository.getNews("sports","2feae0cc3b534626b20a8eaee2b63a03");
    }
    public LiveData<NewsResponce> getNewsRepository(){
        return mutableLiveData;
    }
}
