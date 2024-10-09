package com.example.android_app_demo.coroutine

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R
import kotlin.math.pow


class Demo2 : AppCompatActivity() {

    private lateinit var inputNumber: EditText
    private lateinit var computeButton: Button
    private lateinit var resultTextView: TextView
    lateinit var handler2: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo2)

        inputNumber = findViewById(R.id.inputNumber)
        computeButton = findViewById(R.id.computeButton)
        resultTextView = findViewById(R.id.resultTextView)

        computeButton.setOnClickListener {

            var number = inputNumber.text.toString().toDoubleOrNull()
            val thread = Thread {
                if (number != null) {
                    number = number!!.pow(2)
                    val message: Message = handler2.obtainMessage()
                    val bundle = Bundle()
                    bundle.putString("MyObject", number.toString())
                    message.data = bundle
                    handler2.sendMessage(message)
                }

            }.start()
        }
        val thread2 = Thread {
            Looper.prepare()
            handler2 = Handler(Looper.myLooper()!!)
            { message ->

                val bundle = message.data
                val pow = bundle.getString("MyObject")

                val sum = pow!!.toDouble() + 10.0

                val mainHandler = Handler(Looper.getMainLooper()!!)
                mainHandler.post {
                    resultTextView.text = sum.toString()
                }
            }
            Looper.loop()
        }.start()

    }
}

