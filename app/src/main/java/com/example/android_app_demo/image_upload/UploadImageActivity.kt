package com.example.android_app_demo.image_upload

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_app_demo.R
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File

class UploadImageActivity : AppCompatActivity() {
    private lateinit var upload: Button
    private var selectedImageUri: Uri? = null
    private lateinit var imagePart: MultipartBody.Part
    private lateinit var main: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_image)
        upload = findViewById(R.id.upload_img)
        main = findViewById<ConstraintLayout>(R.id.main)
        upload.setOnClickListener {
            openImagePicker()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        imagePickerLauncher.launch(intent)
    }

    private val imagePickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                selectedImageUri = data?.data
                Log.d("path", selectedImageUri?.path.toString())
                val file = File(selectedImageUri?.path ?: "")
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

                Instance.apiInterface.addImage(imagePart)
                    .enqueue(object : retrofit2.Callback<ResponseBody> {
                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: retrofit2.Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {

                                Snackbar.make(
                                    main,
                                    "Image Uploaded Successfully",
                                    Snackbar.LENGTH_LONG
                                )
                                    .show()
                            }

                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            Snackbar.make(
                                main,
                                "Image does not Uploaded",
                                Snackbar.LENGTH_LONG
                            ).show()

                            Log.d("throw", t.stackTraceToString())
                        }
                    })


            }

        }
}
