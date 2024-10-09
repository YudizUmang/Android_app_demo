package com.example.android_app_demo.notification

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class NotificationData : AppCompatActivity() {
    lateinit var title: TextView
    lateinit var body: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notification_data)
        title = findViewById(R.id.notification_title)
        body = findViewById(R.id.notification_body)

        title.text = intent.getStringExtra("title")
        body.text = intent.getStringExtra("body")

    }
}