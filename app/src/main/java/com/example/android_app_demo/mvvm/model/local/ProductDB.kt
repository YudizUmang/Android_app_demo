package com.example.android_app_demo.mvvm.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_app_demo.mvvm.model.data.Product
import com.example.android_app_demo.mvvm.model.data.RatingTypeConverter


@Database(entities = [Product::class], version = 1)
@TypeConverters(RatingTypeConverter::class)
abstract class ProductDB : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        const val DB_NAME = "product_database"
    }
}