package com.example.quicktestapplication.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ToastWrapper {
    private val handler = Handler(Looper.getMainLooper())
    fun showToast(context: Context, msg: String, showLong : Boolean = false) {
        handler.post {
            Toast.makeText(context, msg, if (showLong)Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
        }
    }
}