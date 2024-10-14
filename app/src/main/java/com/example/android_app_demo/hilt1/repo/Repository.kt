package com.example.android_app_demo.hilt1.repo

import android.util.Log
import javax.inject.Inject

class Repository @Inject constructor() {
    fun fecthData(): String {
        Log.d("fetchData", "ABCD")
        return "ABCD"
    }
}