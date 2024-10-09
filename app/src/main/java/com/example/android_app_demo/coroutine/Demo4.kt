package com.example.android_app_demo.coroutine

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.android_app_demo.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream

class Demo4 : AppCompatActivity() {
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_demo4)
        textView = findViewById(R.id.display_text)

        lifecycleScope.launch {
            lateinit var myOutput: String
            withContext(Dispatchers.IO) {
                val myInputStream: InputStream
                try {
                    myInputStream = assets.open("MyText.txt")
                    val size: Int = myInputStream.available()
                    val buffer = ByteArray(size)
                    myInputStream.read(buffer)
                    myOutput = String(buffer)


                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            withContext(Dispatchers.Main) {
                textView.text = myOutput
            }
        }
    }
}
