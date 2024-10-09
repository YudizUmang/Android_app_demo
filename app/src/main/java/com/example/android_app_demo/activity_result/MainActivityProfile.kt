package com.example.android_app_demo.activity_result

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_demo.R

class MainActivityProfile : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var editButton: Button

    private lateinit var imageUri: Uri
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_profile)

        profileImageView = findViewById(R.id.profile_image)
        nameEditText = findViewById(R.id.name_edit_text)
        ageEditText = findViewById(R.id.age_edit_text)
        cityEditText = findViewById(R.id.city_edit_text)
        editButton = findViewById(R.id.edit_button)

        nameEditText.setText("Umang")
        ageEditText.setText("22")
        cityEditText.setText("Rajkot")


        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    imageUri = result.data?.data!!
                    profileImageView.setImageURI(imageUri)
                }
            }


        editButton.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("name", nameEditText.text.toString())
            intent.putExtra("age", ageEditText.text.toString())
            intent.putExtra("dob", cityEditText.text.toString())
            startActivityForResult(intent, 1)
        }

        profileImageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            resultLauncher.launch(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            val updatedName = data?.getStringExtra("updated_name") ?: nameEditText.text.toString()
            val updatedAge = data?.getStringExtra("updated_age") ?: ageEditText.text.toString()
            val updatedCity = data?.getStringExtra("updated_city") ?: cityEditText.text.toString()

            nameEditText.setText(updatedName)
            ageEditText.setText(updatedAge)
            cityEditText.setText(updatedCity)
        }
    }
}