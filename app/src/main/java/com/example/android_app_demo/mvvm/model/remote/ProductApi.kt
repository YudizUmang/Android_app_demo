package com.example.android_app_demo.mvvm.model.remote

import com.example.android_app_demo.mvvm.model.data.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products")
    suspend fun getProduct(): Response<List<Product>?>
}