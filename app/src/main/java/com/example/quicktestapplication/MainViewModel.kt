package com.example.quicktestapplication

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicktestapplication.coroutines.CoroutinePlatformTracker
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel : ViewModel() {
    private val a = null

    suspend fun fakeSuspend(): Int = suspendCancellableCoroutine { cont ->
        viewModelScope.launch {
            delay(2000)
            cont.resume(0)
            if (cont.isActive) {
                cont.resume(0)
            }
        }
//        cont.invokeOnCancellation {
//            Log.d("sangnd", "on Cancellation")
//        }
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

    suspend fun getContextSuspendFunction(context: Context) {
        Toast.makeText(context, coroutineContext[CoroutineName]?.name, Toast.LENGTH_SHORT).show()
    }

}