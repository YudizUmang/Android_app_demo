package com.example.android_app_demo

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidAppDemo : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Application", "AndroidAppDemo")
    }
}