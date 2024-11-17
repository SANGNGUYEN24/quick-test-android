package com.example.quicktestapplication.coroutines

import androidx.collection.ArrayMap
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import org.junit.Test
import kotlin.coroutines.CoroutineContext

class AddContextTest {
    @Test
    fun main() {
        val ctx1: CoroutineContext = CoroutineName("Name1")
        println(ctx1[CoroutineName]?.name) // Name1
        println(ctx1[Job]?.isActive) // null
        println("---")
        val ctx2: CoroutineContext = Job()
        println(ctx2[CoroutineName]?.name) // null
        println(ctx2[Job]?.isActive) // true, because "Active"
        println("---")
        // is the default state of a job created this way
        val ctx3 = ctx1 + ctx2
        println(ctx3[CoroutineName]?.name) // Name1
        println(ctx3[Job]?.isActive) // true
        println("---")
        val ctx4 = CoroutineName("Name4")
        val ctx5 = ctx3 + ctx4
        println(ctx5[CoroutineName]?.name) // Name4
        println(ctx5[Job]?.isActive) // true
    }
}