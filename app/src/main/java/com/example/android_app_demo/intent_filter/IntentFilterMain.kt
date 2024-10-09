package com.example.android_app_demo.intent_filter

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_app_demo.R

class IntentFilterMain : AppCompatActivity() {
    lateinit var intentView: ImageView
    lateinit var intetGmailBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_intent_filter_main)
        intentView = findViewById(R.id.intent_data)
        intetGmailBtn = findViewById(R.id.mail_btn)

        val main = findViewById<ConstraintLayout>(R.id.main)
        val intent = intent
        val action = intent.action
        val type = intent.type

//        if (Intent.ACTION_SEND.equals(action) && type != null) {
//            val text = intent.getStringExtra(Intent.EXTRA_TEXT)
//            if (text != null) {
//                intentTextView.text = text
//            } else {
//                Snackbar.make(main, "No Data Found", Snackbar.LENGTH_LONG)
//            }
//
//        }

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                val imageUri: Uri? = intent.getParcelableExtra(Intent.EXTRA_STREAM)
                if (imageUri != null) {
                    intentView.setImageURI(imageUri)
                }
            }
        }
        intetGmailBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val recipients = arrayOf("recipient@gmail.com")
            intent.putExtra(Intent.EXTRA_EMAIL, recipients)
            intent.putExtra(Intent.EXTRA_SUBJECT, "ABCDEFG")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Good AfterNoon all, I'm Writing this mail to express ...."
            )
            intent.setType("text/html")
            intent.setPackage("com.google.android.gm")
            startActivity(Intent.createChooser(intent, "Send mail"))
        }
    }
}