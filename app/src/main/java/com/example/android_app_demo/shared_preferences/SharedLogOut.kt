package com.example.android_app_demo.shared_preferences

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_app_demo.R
import com.google.android.material.snackbar.Snackbar

class SharedLogOut : AppCompatActivity() {
    lateinit var nameText: TextView
    lateinit var emailText: TextView
    lateinit var ageText: TextView
    lateinit var logOutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_preference_logout)
        nameText = findViewById(R.id.text_name)
        emailText = findViewById(R.id.text_email)
        ageText = findViewById(R.id.text_age)
        logOutBtn = findViewById(R.id.logout_btn)
        val main = findViewById<ConstraintLayout>(R.id.constraint)
        val sharedPref = applicationContext.getSharedPreferences("MyPref", 0)
        val editor = sharedPref.edit()

        if (sharedPref.contains("name") && sharedPref.contains("email") && sharedPref.contains("age")) {
            nameText.text = sharedPref.getString("name", null)
            emailText.text = sharedPref.getString("email", null)
            ageText.text = sharedPref.getString("age", null)
        } else {
            Snackbar.make(main, "No data found", Snackbar.LENGTH_LONG).show()
            finish()
        }

        logOutBtn.setOnClickListener {
            editor.remove("name")
            editor.remove("email")
            editor.remove("age")
            editor.apply()
            finish()
        }


    }
}