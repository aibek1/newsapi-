package com.hfad.newsapiisubbota.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.hfad.newsapiisubbota.R;
import com.hfad.newsapiisubbota.adapters.NewsAdapter;
import com.hfad.newsapiisubbota.categories.Const;
import com.hfad.newsapiisubbota.categories.News;
import com.hfad.newsapiisubbota.net.ApiClient;
import com.hfad.newsapiisubbota.net.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivitySub extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe;

    ApiService mApiService;

    RecyclerView mRecyclerView;

    NewsAdapter mNewsAdapter;

    Call<News> mNewsCall;

    List<News.Article>mArticleList;

    String newsType,url;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mProgressBar=findViewById(R.id.newsListProgressbar);
        mProgressBar.setIndeterminate(true);
        mApiService = ApiClient.getClient().create(ApiService.class);
        swipe = findViewById(R.id.swipe_list);

        Intent received=getIntent();
        newsType = received.getStringExtra(Const.NEWS_TYPE);

        mRecyclerView = findViewById(R.id.newsListRecyclerView);


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        swipe.setOnRefreshListener(this);

        swipe.setColorSchemeResources(
                R.color.colorAccent,
                R.color.green,
                R.color.blue,
                R.color.orange);



/////////////////////////////////////////////////////////////////
        load();
    }
    private void lodaing(){}
    private void load(){
        mArticleList = new ArrayList<>();
        mNewsAdapter=new NewsAdapter(this,mArticleList,newsType);

        mRecyclerView.setAdapter(mNewsAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        if(newsType.equals("top_news")){
            swipe.setRefreshing(true);////////////////////////////////////////////////////////////////////////////////
             mNewsCall=mApiService.topNews(getResources().getString(R.string.country),getResources().getString(R.string.api_key));
            mNewsCall.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    swipe.setRefreshing(false);/////////////////////////////////////////////////////////////////////
                    if(!response.isSuccessful()){
                        mNewsCall = call.clone();
                        mNewsCall.enqueue(this);
                        return;
                    }
                    mProgressBar.setVisibility(View.GONE);
                    if(response.body()==null)return;
                    mArticleList.addAll(response.body().getArticles());
                    mNewsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<News> call, Throwable throwable) {
                    swipe.setRefreshing(false);/////////////////////////////////////////////////////////////////////
                    mProgressBar.setVisibility(View.GONE);
                }
            });
        } else{
            swipe.setRefreshing(true);////////////////////////////////////////////////////////////////////////////////
            mNewsCall = mApiService.topNewsCategory(getResources().getString(R.string.country), newsType, getResources().getString(R.string.api_key));
            mNewsCall.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    swipe.setRefreshing(false);/////////////////////////////////////////////////////////////////////
                    if (!response.isSuccessful()) {
                        mNewsCall = call.clone();
                        mNewsCall.enqueue(this);
                        return;
                    }
                    mProgressBar.setVisibility(View.GONE);
                    if (response.body() == null) return;
                    mArticleList.addAll(response.body().getArticles());
                    mNewsAdapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<News> call, Throwable throwable) {
                    swipe.setRefreshing(false);/////////////////////////////////////////////////////////////////////
                    mProgressBar.setVisibility(View.GONE);
                }
            });
        }
    }
    @Override
    public void onRefresh(){
        swipe.setRefreshing(true);
        mArticleList=null;
        load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mNewsCall!=null){
            mNewsCall.cancel();
        }
    }
}
