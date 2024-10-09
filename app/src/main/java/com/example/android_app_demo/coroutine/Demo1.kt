package com.example.android_app_demo.coroutine

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class Demo1 : AppCompatActivity() {

    private lateinit var editTextNumber1: EditText
    private lateinit var editTextNumber2: EditText
    private lateinit var buttonAdd: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo1)

        editTextNumber1 = findViewById(R.id.editTextNumber1)
        editTextNumber2 = findViewById(R.id.editTextNumber2)
        buttonAdd = findViewById(R.id.buttonAdd)
        textViewResult = findViewById(R.id.textViewResult)

//        buttonAdd.setOnClickListener {
//
//            val number1 = editTextNumber1.text
//            val number2 = editTextNumber2.text
//            if (number1.isEmpty() || number2.isEmpty()) {
//                textViewResult.text = getString(R.string.enter_valid_number)
//
//            } else {
//                Thread {
//                    val result = number1.toString().toInt() + number2.toString().toInt()
//                    Handler(Looper.getMainLooper()).post {
//                        textViewResult.text = "Result: $result"
//                    }
//                }.start()
//            }
//        }
        Thread {
            Thread.sleep(1000)
            runOnUiThread {
                textViewResult.text = "abcd"
            }
        }.start()
    }
}
