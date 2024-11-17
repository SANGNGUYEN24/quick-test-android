package com.example.quicktestapplication

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {

    suspend fun fakeSuspend(): Int = suspendCoroutine { cont ->
        cont.resume(0)
    }
}