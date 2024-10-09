package com.example.android_app_demo.basic_ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class basic_ui : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var surname: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "OnCreate: 1st Screen")
        enableEdgeToEdge()
        setContentView(R.layout.basic_ui)
        name = findViewById(R.id.name)
        surname = findViewById(R.id.surname)
        val btn = findViewById<Button>(R.id.submit_button)

        btn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (name.length() != 0 && surname.length() != 0) {

                    Log.d("value of the input field", "${name.text} ${surname.text}")
                    val bundle = Bundle()
                    bundle.putString("name", name.text.toString())
                    bundle.putString("surname", surname.text.toString())
                    startActivity(
                        Intent(
                            this@basic_ui,
                            SecondScreen::class.java
                        ).putExtras(bundle)
                    )
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "onStart: 1st Screen")
    }

    override fun onResume() {
        super.onResume()
        name.text.clear()
        surname.text.clear()
        Log.d("onResume", "onResume: 1st Screen")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "onStop: 1st Screen")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause: 1st Screen")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy: 1st Screen")
    }
}