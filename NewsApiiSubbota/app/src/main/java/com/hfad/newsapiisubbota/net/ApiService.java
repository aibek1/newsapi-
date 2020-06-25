package com.hfad.newsapiisubbota.net;


import com.hfad.newsapiisubbota.categories.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("top-headlines")
    Call<News> topNews(@Query("country") String country, @Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<News> topNewsCategory(@Query("country") String country,
                               @Query("category") String category,
                               @Query("apiKey") String apiKey);

}
