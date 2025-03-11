package com.example.workmanagerdemo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanagerdemo.worker.CommentWorker
import com.example.workmanagerdemo.worker.PostWorker
import com.example.workmanagerdemo.worker.UserWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ViewModel
class MainViewModel(application: Application) : AndroidViewModel(application) {
    /*private val workManager = WorkManager.getInstance(application)
    private val _workStatus = MutableLiveData<String>()
    val workStatus: LiveData<String> get() = _workStatus

    fun startWork() {
        val workRequest1 = OneTimeWorkRequestBuilder<PostWorker>().build()
        val workRequest2 = OneTimeWorkRequestBuilder<UserWorker>().build()
        val workRequest3 = OneTimeWorkRequestBuilder<CommentWorker>().build()

        workManager.beginWith(workRequest1)
            .then(workRequest2)
            .then(workRequest3)
            .enqueue()
    }*/

    private val workManager = WorkManager.getInstance(application)
    private val _workStatus = MutableStateFlow("Idle")
    val workStatus: StateFlow<String> get() = _workStatus

    fun startWork() {
        val workRequest1 = OneTimeWorkRequestBuilder<PostWorker>().build()
        val workRequest2 = OneTimeWorkRequestBuilder<UserWorker>().build()
        val workRequest3 = OneTimeWorkRequestBuilder<CommentWorker>().build()

        val workContinuation = workManager.beginWith(workRequest1)
            .then(workRequest2)
            .then(workRequest3)

        workContinuation.enqueue()

        workContinuation.workInfosLiveData.observeForever { workInfos ->
            if (workInfos.all { it.state.isFinished }) {
                viewModelScope.launch {
                    _workStatus.value = "Work Completed"
                }
            }
        }

        viewModelScope.launch {
            _workStatus.value = "Work Started"
        }
    }
}