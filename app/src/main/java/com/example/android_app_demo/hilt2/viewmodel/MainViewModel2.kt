package com.example.android_app_demo.hilt2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.android_app_demo.hilt2.repo.FireBaseRepository
import com.example.android_app_demo.hilt2.repo.SQLRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel2 @Inject constructor(private val repo: FireBaseRepository) : ViewModel() {

    @Inject
    lateinit var sqlRepo: SQLRepository
    fun getData(): String {
        Log.d("ViewModel", "GetData")
        return if (repo.saveUser("abcd@gmail.com") && sqlRepo.saveUsertoDB("abcd@gmail.com")) "User saved successfully in both db" else "User does not saved to DB"


    }
}