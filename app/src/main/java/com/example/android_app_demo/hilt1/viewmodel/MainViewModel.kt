package com.example.android_app_demo.hilt1.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android_app_demo.hilt1.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    fun getData(): String {
        Log.d("ViewModel", "GetData")
        val data = repo.fecthData()
        return data
    }
}