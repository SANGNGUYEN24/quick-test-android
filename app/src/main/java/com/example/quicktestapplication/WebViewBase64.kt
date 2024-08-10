package com.example.quicktestapplication

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun WebViewBase64() {
    var showWebView by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showWebView = !showWebView }) {
            Text(text = "Show WebView")
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (showWebView) {
            val base64 = getBase64(LocalContext.current)
            Log.d("sangnd5","base64 length = ${base64.length}")
            WebViewComponent(base64)
        }
    }
}

private fun getBase64(context: Context): String {
    return try {
        val jpgInputStream = context.resources.openRawResource(R.raw.jpg_500kb)
        Base64.encodeToString(jpgInputStream.readBytes(), Base64.DEFAULT)
    } catch (e: Exception) {
        ""
    }
}