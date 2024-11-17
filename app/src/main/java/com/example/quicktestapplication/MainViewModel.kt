package com.example.quicktestapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {
    private val a = null

    suspend fun fakeSuspend(): Int = suspendCancellableCoroutine { cont ->
        cont.resume(0)
        if (cont.isActive) {
            cont.resume(0)
        }
    }

    suspend fun testLoopSuspend() {
        repeat(3) {
            suspendCoroutine()
        }
    }

    private suspend fun suspendCoroutine(): Int = suspendCoroutine {
        it.resume(1)
    }

    /**
     * Cannot try catch outside a coroutine, app still crash
     */
    fun testException() {
        // WRONGGGGGG, must try catch inside the coroutine
        try {
            viewModelScope.launch {
                a!!
            }
        }catch (e: Exception) {

        }
    }

}