package com.example.android_app_demo.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class NetworkChargeWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        delay(5000)
        Log.d("Worker", "Task completed successfully.")
        return Result.success()
    }
}
