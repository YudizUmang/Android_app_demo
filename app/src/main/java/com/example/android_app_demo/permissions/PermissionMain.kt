package com.example.android_app_demo.permissions

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.android_app_demo.R

class PermissionMain : AppCompatActivity() {
    lateinit var locationButtton: Button
    lateinit var locationText: TextView
    lateinit var openOtherAppButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_permission_main)
        locationButtton = findViewById(R.id.location_btn)
        locationText = findViewById(R.id.location_text)
        openOtherAppButton = findViewById(R.id.open_other_app)

        locationButtton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    10
                )
                locationText.text = "Ahmedabad"
            } else {
                locationText.text = "Ahmedabad"
            }
        }

        openOtherAppButton.setOnClickListener {
            if (checkSelfPermission("com.example.permissiondemo.permission.ACCESS") == PackageManager.PERMISSION_GRANTED) {
                val intent =
                    packageManager.getLaunchIntentForPackage("com.example.permissiondemo")
                startActivity(
                    intent
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        "com.example.permissiondemo.permission.ACCESS"
                    ),
                    10
                )
            }

        }
    }
}