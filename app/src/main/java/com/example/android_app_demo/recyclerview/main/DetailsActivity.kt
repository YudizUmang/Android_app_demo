package com.example.android_app_demo.recyclerview.main

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_details_main)

        val name = intent.getStringExtra("NAME")
        val number = intent.getStringExtra("NUMBER")
        val email = intent.getStringExtra("EMAIL")
        val city = intent.getStringExtra("CITY")

        findViewById<TextView>(R.id.text_name).text = name
        findViewById<TextView>(R.id.text_number).text = number
        findViewById<TextView>(R.id.text_email).text = email
        findViewById<TextView>(R.id.text_city).text = city
    }
}
