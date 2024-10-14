package com.example.android_app_demo.mvvm.view

import DataRepository
import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.android_app_demo.R
import com.example.android_app_demo.mvvm.model.local.ProductDB
import com.example.android_app_demo.mvvm.model.remote.ProductApi
import com.example.android_app_demo.mvvm.model.remote.ProductRetrofitInstance
import com.example.android_app_demo.mvvm.view.adapter.ProductAdapter
import com.example.android_app_demo.mvvm.viewmodel.MainViewModel
import com.example.android_app_demo.mvvm.viewmodel.MainViewModelFactory


class ProductDisplay : AppCompatActivity() {
    private lateinit var apiService: ProductApi

    private lateinit var db: ProductDB

    private lateinit var repo: DataRepository
    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var manager: LinearLayoutManager

    //    var isScrolling = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        apiService = ProductRetrofitInstance.apiInterface

        db = Room.databaseBuilder(
            applicationContext,
            ProductDB::class.java, ProductDB.DB_NAME
        ).build()

        repo = DataRepository(apiService, db)
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(repo))[MainViewModel::class.java]
        setContentView(R.layout.activity_product_display)
        viewModel.displayProductVm(this)

        recyclerView = findViewById(R.id.product_list)
        progressBar = findViewById(R.id.progress)
        manager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager

        viewModel.productData.observe(this, Observer { products ->
            productAdapter = ProductAdapter(this, products)
            recyclerView.adapter = productAdapter

        })
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
//                    isScrolling = true
//                }
//            }
//
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val currentItems = manager.childCount
//                val totalItems = manager.getItemCount()
//                val scrollOutItems = manager.findFirstVisibleItemPosition()
//
//                if (isScrolling && ((currentItems + scrollOutItems) == totalItems)) {
//                    isScrolling = false
//                }
//            }
//        })

    }
}