package com.example.android_app_demo.activity_result

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class EditActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        nameEditText = findViewById(R.id.name_edit_text)
        ageEditText = findViewById(R.id.age_edit_text)
        cityEditText = findViewById(R.id.city_edit_text)
        saveButton = findViewById(R.id.save_button)

        val name = intent.getStringExtra("name") ?: ""
        val age = intent.getStringExtra("age") ?: ""
        val dob = intent.getStringExtra("dob") ?: ""

        nameEditText.setText(name)
        ageEditText.setText(age)
        cityEditText.setText(dob)

        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedAge = ageEditText.text.toString()
            val updatedDob = cityEditText.text.toString()

            val intent = Intent()
            intent.putExtra("updated_name", updatedName)
            intent.putExtra("updated_age", updatedAge)
            intent.putExtra("updated_dob", updatedDob)
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}