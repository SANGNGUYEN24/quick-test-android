package com.example.quicktestapplication

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WebViewComponent(base64: String) {
    val context = LocalContext.current
    AndroidView(factory = {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            val data = """
                <html>
                <body>
                <img src="data:image/jpeg;charset=utf-8;base64, $base64" />
                <p>${"a".repeat(2_100_000 - base64.length)}.</p>
                </body>
                </html>
            """.trimIndent()
            Log.d("sangnd5", "data length = ${data.length}")
            loadData(data,"text/html", "UTF-8")
        }
    })
}