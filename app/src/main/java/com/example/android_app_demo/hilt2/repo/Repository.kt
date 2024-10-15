package com.example.android_app_demo.hilt2.repo

import android.util.Log
import javax.inject.Inject

class FireBaseRepository @Inject constructor() {
    fun saveUser(email: String): Boolean {
        Log.d("Save", "User saved to firebase successfully")
        return true
    }
}

class SQLRepository @Inject constructor() {
    fun saveUsertoDB(email: String): Boolean {
        Log.d("Save", "User saved to firebase successfully")
        return true
    }
}