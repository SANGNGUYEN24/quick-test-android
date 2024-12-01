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
}