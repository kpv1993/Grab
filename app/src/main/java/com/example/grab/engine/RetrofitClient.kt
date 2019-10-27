package com.example.grab.engine

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class RetrofitClient{
    companion object Factory{
        fun getRetrofitInstance(): Retrofit{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/")
                .build()

            return retrofit
        }
    }
}