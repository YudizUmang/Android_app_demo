package com.example.android_app_demo.workmanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.android_app_demo.R

class Upload : AppCompatActivity() {
    private lateinit var txtView: TextView
    private lateinit var btnWork: Button
    private lateinit var request: OneTimeWorkRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
        txtView = findViewById(R.id.txt_work)
        btnWork = findViewById(R.id.do_work_btn)

        btnWork.setOnClickListener {
            request = OneTimeWorkRequest.from(UploadWorker::class.java)
            WorkManager.getInstance(this)
                .enqueue(request)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer { resultInfo ->
                    if (resultInfo != null) {
                        when (resultInfo.state) {
                            WorkInfo.State.ENQUEUED -> txtView.text = "Work is enqueued."
                            WorkInfo.State.RUNNING -> txtView.text = "Work is running."
                            WorkInfo.State.SUCCEEDED -> txtView.text =
                                "Upload completed successfully."

                            WorkInfo.State.FAILED -> txtView.text = "Upload failed."
                            else -> txtView.text = "Something went wrong"
                        }
                    }
                })
        }
    }
}