package com.example.android_app_demo.data_binding

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.android_app_demo.R
import com.example.android_app_demo.databinding.ActivityDataBindingBinding
import java.util.Date

class DataBinding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityDataBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_data_binding)

        binding.user =
            ContextCompat.getDrawable(this, R.drawable.avatar)?.let {
                MyUser(
                    "Umang",
                    "Chovatiya",
                    it,
                    Date(2002, 10, 25)
                )
            }
        Log.d("date", Date(2002, 10, 25).toString())
    }
}