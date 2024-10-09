package com.example.android_app_demo.retrofit

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    var name: String,

    @SerializedName("companyname")
    var companyName: String,

    @SerializedName("img")
    var img: String,

    @SerializedName("age")
    var age: Int,

    @SerializedName("id")
    val userId: String
)
