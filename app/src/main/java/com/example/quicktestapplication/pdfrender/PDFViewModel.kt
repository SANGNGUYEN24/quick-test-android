package com.example.quicktestapplication.pdfrender

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfRenderer
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.io.File
import java.io.FileOutputStream

class PDFViewModel : ViewModel() {
    suspend fun createPageBitmaps(
        pdfRenderer: PdfRenderer,
        deviceDpi: Int,
        screenWidth: Int
    ): List<Bitmap> = withContext(Dispatchers.IO) {
        val result = mutableListOf<Bitmap>()
        for (i in 0 until pdfRenderer.pageCount) {
            val page = pdfRenderer.openPage(i)
            val fullWidth = page.width / 72 * deviceDpi
            val fullHeight = page.height / 72 * deviceDpi
            val ratio = fullWidth.toFloat() / fullHeight

            val defaultWidth = 2 * screenWidth.toFloat() / 3
            val defaultHeight = defaultWidth / ratio

            val bitmap = Bitmap.createBitmap(defaultWidth.toInt(), defaultHeight.toInt(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = Paint()
            paint.color = Color.WHITE
            canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), paint)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            result.add(bitmap)
            log("DPI = $deviceDpi")
            log(
                "Index = $i, " +
                        "\nwidth: $fullWidth dots, $fullWidth points, ${"%.2f".format(fullWidth.toFloat() / 72)} inches" +
                        "\nheight: $fullHeight dots, inches = ${"%.2f".format(fullHeight.toFloat() / 72)} inches"
            )
            page.close()
        }
        result.toList()
    }

    suspend fun downloadPdf(baseUrl: String, url: String, cacheDir: String, useCache: Boolean = true): File {
        val pdfFile = File(cacheDir, "download.pdf")
        if (pdfFile.exists() && !useCache) {
            return pdfFile
        }
        return withContext(Dispatchers.IO) {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClient.Builder().build())
                .build()

            val pdfService = retrofit.create(PDFService::class.java)
            val responseBody = pdfService.downloadPDF(url)
            responseBody.byteStream().use { inputStream ->
                FileOutputStream(pdfFile).use { outputStream ->
                    inputStream.copyTo(outputStream)
                }
            }
            return@withContext pdfFile
        }
    }

    private fun log(msg: String) {
        Log.d("PDFRenderer", msg)
    }
}