package com.example.android_app_demo.mvvm.model.data

import androidx.room.TypeConverter
import com.google.gson.Gson

class RatingTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromRating(rating: Rating?): String? {
        return rating?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toRating(ratingString: String?): Rating? {
        return ratingString?.let { gson.fromJson(it, Rating::class.java) }
    }
}
