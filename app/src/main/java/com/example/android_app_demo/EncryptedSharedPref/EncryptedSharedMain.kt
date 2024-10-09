package com.example.android_app_demo.EncryptedSharedPref

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.android_app_demo.R
import com.google.android.material.snackbar.Snackbar

class EncryptedSharedMain : AppCompatActivity() {

    lateinit var nameEditetext: EditText
    lateinit var emailEditText: EditText
    lateinit var ageEditText: EditText
    lateinit var submitButton: Button
    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.shared_preference_form)
        val main = findViewById<ConstraintLayout>(R.id.constraint)
        nameEditetext = findViewById(R.id.name_edit_text)
        emailEditText = findViewById(R.id.email_edit_text)
        ageEditText = findViewById(R.id.age_edit_text)
        submitButton = findViewById(R.id.submit_button)
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        sharedPref = EncryptedSharedPreferences.create(
            "encryptedPref",
            masterKeyAlias,
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        editor = sharedPref.edit()

        submitButton.setOnClickListener {
            if (nameEditetext.text.isEmpty() || emailEditText.text.isEmpty() || ageEditText.text.isEmpty()) {
                Snackbar.make(main, "Every field should be filled", Snackbar.LENGTH_LONG).show()
            } else {
                editor.putString("name", nameEditetext.text.toString())
                editor.putString("email", emailEditText.text.toString())
                editor.putString("age", ageEditText.text.toString())
                editor.apply()
                navigateToSecondActivity()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (sharedPref.contains("name") && sharedPref.contains("email") && sharedPref.contains("age")) {
            navigateToSecondActivity()
        } else {
            editor.remove("name")
            editor.remove("email")
            editor.remove("age")
            editor.apply()
        }
    }

    fun navigateToSecondActivity() {
        startActivity(
            Intent(this, EncryptedSharedLogOut::class.java)
        )
    }
}