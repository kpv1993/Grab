package com.example.grab.engine

import android.util.Log
import com.example.grab.bus.RxBus
import com.example.grab.pojo.Article
import com.example.grab.pojo.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Engine{

    private val TAG = "Engine"

    fun firstRequest(language: String, since: String){
        Log.e(TAG, "firstRequest")
        val service = RetrofitClient.getRetrofitInstance().create(RestInterface::class.java)
        val call = service.getFirstQuery(language, since)

        call.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>?, t: Throwable?) {
                Log.e("retrofit", "call failed")
                RxBus.publish("Sorry, try again")
            }

            override fun onResponse(call: Call<News>?, response: Response<News>?) {
                val articles: List<Article>? = response?.body()?.articles
                Log.e(TAG, articles.toString())
                RxBus.publish(articles!!)
            }

        })
    } 
}