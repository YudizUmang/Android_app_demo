package com.example.android_app_demo.workmanager

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.android_app_demo.R

class NetworkCharge : AppCompatActivity() {
    private lateinit var txtView: TextView
    private lateinit var btnWork: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        txtView = findViewById(R.id.txt_work)
        btnWork = findViewById(R.id.do_work_btn)

        btnWork.setOnClickListener {

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .build()

            val request = OneTimeWorkRequestBuilder<NetworkChargeWorker>()
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(this).enqueue(request)


            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer { workInfo ->
                    if (workInfo != null) {
                        when (workInfo.state) {
                            WorkInfo.State.ENQUEUED -> {
                                txtView.text = "Work is enqueued."
                            }

                            WorkInfo.State.RUNNING -> {
                                txtView.text = "Work is running..."
                            }

                            WorkInfo.State.SUCCEEDED -> {
                                txtView.text = "Task completed successfully."
                            }

                            WorkInfo.State.FAILED -> {
                                txtView.text = "Task failed."
                            }

                            WorkInfo.State.CANCELLED -> {
                                txtView.text = "Task was cancelled."
                            }

                            else -> {}
                        }
                    }
                })
        }
    }
}
