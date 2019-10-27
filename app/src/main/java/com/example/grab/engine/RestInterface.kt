package com.example.grab.engine

import com.example.grab.pojo.Article
import com.example.grab.pojo.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestInterface {
    @GET("v2/top-headlines")
    fun getFirstQuery(@Query("country") country: String,
                      @Query("apiKey") apikey: String): Call<News>

}