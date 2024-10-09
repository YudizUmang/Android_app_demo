package com.example.android_app_demo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://caa04827ea0d080cfeb5.free.beeceptor.com")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiInterface by lazy {
        retrofit.create(UserApi::class.java)
    }
}