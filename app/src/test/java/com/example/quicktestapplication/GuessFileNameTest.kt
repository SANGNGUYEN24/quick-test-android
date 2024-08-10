package com.example.quicktestapplication

import android.webkit.URLUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GuessFileNameTest {
    @Test
    fun `test guess file name`() {
        val url = "https://drive.usercontent.google.com/download?id=1Q31hktEZI1DcVEAWGpb4k6X7TqFIHlqv&export=download"
        val contentDisposition = "attachment; filename=\"sample.pdf\""
        val mimeType = "application/octet-stream"
        val fileName = URLUtil.guessFileName(url, contentDisposition, mimeType)
        println("File name: $fileName")
    }
}
