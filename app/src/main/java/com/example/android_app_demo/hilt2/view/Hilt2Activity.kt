package com.example.android_app_demo.hilt2.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R
import com.example.android_app_demo.hilt2.viewmodel.MainViewModel2
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Hilt2Activity : AppCompatActivity() {
    private lateinit var hilt2_text: TextView
    private val viewModel2: MainViewModel2 by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hilt2)
        hilt2_text = findViewById(R.id.hilt2_text)
        hilt2_text.text = viewModel2.getData()

    }
}