package com.example.android_app_demo.image_upload

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://8d28-103-254-35-170.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiInterface by lazy {
        retrofit.create(ImageApi::class.java)
    }
}