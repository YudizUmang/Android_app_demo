package com.example.android_app_demo.image_upload

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {
    @Multipart
    @POST("/yudiz_testing/image_upload.php")
    fun addImage(@Part body: MultipartBody.Part): Call<ResponseBody>
}