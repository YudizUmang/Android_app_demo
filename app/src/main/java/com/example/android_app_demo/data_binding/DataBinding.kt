package com.example.android_app_demo.data_binding

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.android_app_demo.R
import com.example.android_app_demo.databinding.ActivityDataBindingBinding
import java.time.LocalDate

class DataBinding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityDataBindingBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_data_binding)

        val date = LocalDate.of(2002, 10, 25)
        //val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)

        ContextCompat.getDrawable(this, R.drawable.avatar)?.let {
            binding.user = MyUser("Umang", "Chovatiya", it, date)
        }
    }
}
