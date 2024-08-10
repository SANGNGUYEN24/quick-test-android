package com.example.quicktestapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.File
import org.junit.Test

import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
class Base64EncodeTest {
    @Test
    fun testImageJpg() {
        println("---jpg---")
        val root = "/Users/lap15043/AndroidStudioProjects/QuickTestApplication/app/src/main/java/com/example/quicktestapplication/jpg"
        val jpg50kb = File("$root/Sample-jpg-image-50kb.jpg")
        val jpg100kb = File("$root/SampleJPGImage_100kbmb.jpg")
        val jpg200kb = File("$root/SampleJPGImage_200kbmb.jpg")
        val jpg500kb = File("$root/jpg_500kb.jpg")
        val jpg1mb = File("$root/SampleJPGImage_1mbmb.jpg")
        val jpg2mb = File("$root/SampleJPGImage_2mbmb.jpg")
        val jpg3mb = File("$root/3mb-jpg-example-file.jpg")
        val jpg5mb = File("$root/SampleJPGImage_5mbmb.jpg")
        val jpg10mb = File("$root/SampleJPGImage_10mbmb.jpg")
        val jpg15mb = File("$root/SampleJPGImage_15mbmb.jpg")
        val jpg20mb = File("$root/SampleJPGImage_20mbmb.jpg")
        val jpg30mb = File("$root/SampleJPGImage_30mbmb.jpg")
        val jpgs = listOf(jpg50kb, jpg100kb, jpg200kb, jpg500kb, jpg1mb, jpg2mb, jpg3mb, jpg5mb /*jpg10mb, jpg15mb, jpg20mb, jpg30mb*/)

        jpgs.forEach { jpg ->
            jpg.inputStream().use {
                val base64quality100 = Base64.encodeToString(it.readBytes(), Base64.DEFAULT)
                println("${jpg.name}\nBase64 quality 100 = ${base64quality100.length}")
            }
            if (jpg.length() > 500 * 1024) {
                println("Base64 quality 90 = ${getBase64(jpg, 90).length}")
            }
            if (jpg.length() > 500 * 1024) {
                println("Base64 quality 85 = ${getBase64(jpg, 85).length}")
            }
            if (jpg.length() > 500 * 1024) {
                println("Base64 quality 80 = ${getBase64(jpg, 80).length}")
            }
            println("---")
        }
    }

    @Test
    fun testImagePng() {
        println("---png---")
        val root = "/Users/lap15043/AndroidStudioProjects/QuickTestApplication/app/src/main/java/com/example/quicktestapplication/png"
        val png100kb = File("$root/SamplePNGImage_100kbmb.png")
        val png200kb = File("$root/SamplePNGImage_200kbmb.png")
        val png500kb = File("$root/SamplePNGImage_500kbmb.png")
        val png1mb = File("$root/SamplePNGImage_1mbmb.png")
        val png2mb = File("$root/SamplePNGImage_3mbmb.png")
        val png3mb = File("$root/SamplePNGImage_5mbmb.png")
        val pngs = listOf(png100kb, png200kb, png500kb, png1mb, png2mb, png3mb)

        pngs.forEach { png ->
            png.inputStream().use {
                val base64quality100 = Base64.encodeToString(it.readBytes(), Base64.DEFAULT)
                println("${png.name}\nBase64 quality 100 = ${base64quality100.length}")
            }
            if (png.length() > 500 * 1024) {
                println("Base64 quality 90 = ${getBase64(png, 90).length}")
            }
            if (png.length() > 500 * 1024) {
                println("Base64 quality 85 = ${getBase64(png, 85).length}")
            }
            if (png.length() > 500 * 1024) {
                println("Base64 quality 80 = ${getBase64(png, 80).length}")
            }
            println("---")
        }
    }

    @Test
    fun testImageWebp() {
        println("---webp---")
        val root = "/Users/lap15043/AndroidStudioProjects/QuickTestApplication/app/src/main/java/com/example/quicktestapplication/webp"
        val webp50kb = File("$root/file_example_WEBP_50kB.webp")
        val webp250kb = File("$root/file_example_WEBP_250kB.webp")
        val webp500kb = File("$root/file_example_WEBP_500kB.webp")
        val webp1500kb = File("$root/file_example_WEBP_1500kB.webp")
        val webps = listOf(webp50kb, webp250kb, webp500kb, webp1500kb)

        webps.forEach { webp ->
            webp.inputStream().use {
                val base64quality100 = Base64.encodeToString(it.readBytes(), Base64.DEFAULT)
                println("${webp.name}\nBase64 quality 100 = ${base64quality100.length}")
            }
            if (webp.length() > 500 * 1024) {
                println("Base64 quality 90 = ${getBase64(webp, 90).length}")
            }
            if (webp.length() > 500 * 1024) {
                println("Base64 quality 85 = ${getBase64(webp, 85).length}")
            }
            if (webp.length() > 500 * 1024) {
                println("Base64 quality 80 = ${getBase64(webp, 80).length}")
            }
            println("---")
        }
    }



    private fun getBase64(file: File, quality: Int): String {
        val bitmap = BitmapFactory.decodeFile(file.absolutePath)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream)
        bitmap.recycle()
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT)
    }
}