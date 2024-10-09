package com.example.android_app_demo.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {
    @GET("/api/users")
    fun getUser(): Call<List<User>?>

    @POST("/api/users")
    fun addUser(@Body request: User): Call<User?>

    @PUT("/api/users/{id}")
    fun editUser(@Path("id") userId: String, @Body request: User): Call<User?>

    @DELETE("/api/users/{id}")
    fun deleteUser(@Path("id") userId: String): Call<User?>

}