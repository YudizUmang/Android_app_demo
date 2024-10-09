package com.example.android_app_demo.theme

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.android_app_demo.R

class ChangeTheme : AppCompatActivity() {
    private lateinit var light_btn: Button
    private lateinit var dark_btn: Button
    private lateinit var system_btn: Button
    private lateinit var load_webview: Button
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_theme)
        light_btn = findViewById(R.id.light_btn)
        dark_btn = findViewById(R.id.dark_btn)
        system_btn = findViewById(R.id.system_btn)
        load_webview = findViewById(R.id.webview_btn)
        sharedPref = applicationContext.getSharedPreferences("MyTheme", 0)
        editor = sharedPref.edit()




        light_btn.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor.putString("UI_MODE", "UI_MODE_LIGHT").apply()
        }

        dark_btn.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor.putString("UI_MODE", "UI_MODE_DARK").apply()
        }

        system_btn.setOnClickListener {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            editor.putString("UI_MODE", "UI_MODE_SYSTEM").apply()
        }

        load_webview.setOnClickListener {
            startActivity(Intent(this, WebLoad::class.java))
        }

    }
}