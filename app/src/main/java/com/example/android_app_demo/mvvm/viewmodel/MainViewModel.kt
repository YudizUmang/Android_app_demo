package com.example.android_app_demo.mvvm.viewmodel

import DataRepository
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_app_demo.mvvm.model.data.Product
import kotlinx.coroutines.launch


class MainViewModel(private val repo: DataRepository) : ViewModel() {

    val productData: MutableLiveData<MutableList<Product>> by lazy {
        MutableLiveData<MutableList<Product>>()
    }
//    private val _productData = MutableLiveData<MutableList<Product>>()
//    val productData: LiveData<MutableList<Product>> get() = _productData

    fun displayProductVm(context: Context) {
        if (productData.value != null) {
            return
        } else {
            viewModelScope.launch {
                val products = repo.displayProduct(context)
                Log.d("displayProduct", products.toString())
                productData.postValue(products)
            }
        }

    }

}