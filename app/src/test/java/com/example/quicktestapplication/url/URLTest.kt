package com.example.quicktestapplication.url

import org.junit.Test
import java.net.URL

class URLTest {
    @Test
    fun test(){
        val url = URL("https://developer.android.com/reference/android/webkit/WebResourceResponse#WebResourceResponse(java.lang.String,%20java.lang.String,%20int,%20java.lang.String,%20java.util.Map%3Cjava.lang.String,java.lang.String%3E,%20java.io.InputStream)")
        println("----")
        println(url.authority)
        println(url.ref)
        println(url.protocol)
        println(url.path)
        println(url.host)
        println("${url.protocol}://${url.host}/")
    }

    @Test
    fun testMimeTypeDataUrl() {
        var url = "data://image/jpeg;base64..."
        var type: String? = null
        // Data URL: data://image/jpeg;base64...
        val dataUrlPrefix = "data://"
        if (url.startsWith(dataUrlPrefix)) {
            val mimeStart = url.indexOf(dataUrlPrefix)
            val mimeEnd = url.indexOf(';')
            type = url.substring(mimeStart + dataUrlPrefix.length, mimeEnd)
        }
        println("-------")
        println(type)
        println("-------")
    }
}