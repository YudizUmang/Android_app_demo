package com.example.android_app_demo.fontdemo

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class FontMain : AppCompatActivity() {
    lateinit var firstText: TextView
    lateinit var secondText: TextView
    lateinit var changeBtn: Button
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.font_main)
        firstText = findViewById(R.id.first_text)
        secondText = findViewById(R.id.second_text)
        changeBtn = findViewById(R.id.change_font_btn)

        val typeFaceLobster = resources.getFont(R.font.font_demo)
        val typeFaceSuse = resources.getFont(R.font.font_form)

        changeBtn.setOnClickListener {
            flag = !flag
            if (flag) {
                firstText.typeface = typeFaceLobster
                secondText.setTypeface(typeFaceSuse, Typeface.BOLD)
            } else {
                firstText.setTypeface(typeFaceSuse, Typeface.BOLD)
                secondText.typeface = typeFaceLobster
            }
        }
    }
}