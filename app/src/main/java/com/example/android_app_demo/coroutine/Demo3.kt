package com.example.android_app_demo.coroutine

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class Demo3 : AppCompatActivity() {
    private lateinit var counterTextView: TextView
    private lateinit var startButton: Button
    private lateinit var counter: Counter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo3)

        counterTextView = findViewById(R.id.counterTextView)
        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener { startCounting() }
    }

    private fun startCounting() {

        counter = Counter()
        counter.execute()
    }

    inner class Counter : AsyncTask<Void?, Int?, Void?>() {

        override fun onProgressUpdate(vararg values: Int?) {
            counterTextView.text = "Counter: " + values[0]
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            for (i in 0..10) {
                if (i == 10) {
                    counter.cancel(true)
                } else {
                    Thread.sleep(1000)
                    publishProgress(i)
                }
            }

            return null
        }

        override fun onCancelled() {
            super.onCancelled()
            Toast.makeText(this@Demo3, "Counting completed!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}