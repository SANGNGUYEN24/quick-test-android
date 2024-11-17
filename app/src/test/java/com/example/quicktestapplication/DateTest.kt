package com.example.quicktestapplication

import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


class DateTest {
    @Test
    fun test() {
        val sdfFull = SimpleDateFormat("yyMMdd-HHmmss.SSS", Locale.US)
        println("---- ${sdfFull.format(Date(System.currentTimeMillis()))}")
    }
}