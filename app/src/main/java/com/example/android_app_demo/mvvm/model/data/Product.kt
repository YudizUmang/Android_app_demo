package com.example.android_app_demo.mvvm.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "products")
data class Product(

    @PrimaryKey
    @SerializedName("id")
    val id: Long,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Double,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @ColumnInfo(name = "category")
    @SerializedName("category")
    val category: String,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "rating")
    @SerializedName("rating")
    val rating: Rating,
)

data class Rating(
    @ColumnInfo(name = "rate")
    @SerializedName("rate")
    val rate: Double,

    @ColumnInfo(name = "count")
    @SerializedName("count")
    val count: Long,
)

