package com.example.android_app_demo.mvvm.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface: ProductApi by lazy {
        retrofit.create(ProductApi::class.java)
    }
}