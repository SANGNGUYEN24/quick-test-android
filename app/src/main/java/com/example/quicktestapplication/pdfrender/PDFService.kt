package com.example.quicktestapplication.pdfrender

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface PDFService {
    @GET
    suspend fun downloadPDF(@Url url: String): ResponseBody
}