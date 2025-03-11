package com.example.workmanagerdemo.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerdemo.data.network.RetrofitInstance

class CommentWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val response = RetrofitInstance.api.getComments(1)
            Log.d("WorkManager", "Comments: ${response.size}")
            Result.success()
        } catch (e: Exception) {
            Log.e("WorkManager", "Error fetching comments", e)
            Result.retry()
        }
    }
}