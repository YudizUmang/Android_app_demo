package com.example.android_app_demo.basic_ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class SecondScreen : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var surname: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "onCreate: 2nd Activity")
        setContentView(R.layout.second_screen_basicui)
        name = findViewById(R.id.name)
        surname = findViewById(R.id.surname)
        val bundle = intent.extras
        if (bundle != null) {
            name.text = "name = ${bundle.getString("name")}"
            surname.text = "Surname = ${bundle.getString("surname")}"

        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "onStart: 2nd Activity")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume: 2nd Activity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "onStop: 2nd Activity")
    }

    override fun onPause() {
        super.onPause()

        Log.d("onPause", "onPause: 2nd Activity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy: 2nd Activity")
    }
}