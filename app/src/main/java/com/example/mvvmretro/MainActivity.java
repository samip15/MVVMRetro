package com.example.mvvmretro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mvvmretro.Model.NewsItem;
import com.example.mvvmretro.Model.NewsResponce;
import com.example.mvvmretro.adapter.NewsItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<NewsItem> newsItemArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    NewsItemAdapter newsItemAdapter;
    NewsViewModel newsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // retrieving
        retriveNews();
        setUpRv();
    }
    private void setUpRv(){
        if (newsItemAdapter==null){
            // set up rv
            recyclerView = findViewById(R.id.rvNews);
            newsItemAdapter = new NewsItemAdapter(MainActivity.this,newsItemArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(newsItemAdapter);
        }else{
            newsItemAdapter.notifyDataSetChanged();
        }
    }
    private void retriveNews(){
        // view model
        newsViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(NewsViewModel.class);
        newsViewModel.init();
        newsViewModel.getNewsRepository().observe(this, new Observer<NewsResponce>() {
            @Override
            public void onChanged(NewsResponce newsResponce) {
                List<NewsItem> newsItems = newsResponce.getNewsItems();
                newsItemArrayList.addAll(newsItems);
                newsItemAdapter.notifyDataSetChanged();
            }
        });
    }
}