package com.example.quicktestapplication.coroutines

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import org.junit.Before
import org.junit.Test
import kotlin.coroutines.coroutineContext

class JobTest {
    private val exceptionHandler = CoroutineExceptionHandler { context, t ->
        println("CoroutineExceptionHandler received: $context, $t")
    }

    @Before
    fun before() {
        println("---------------")
    }

    @Test
    fun testJobState() = runBlocking {
        // Job created with a builder is active
        val job = Job()
        println(job) // JobImpl{Active}@ADD
        // until we complete it with a method
        job.complete()
        println(job) // JobImpl{Completed}@ADD
        // launch is initially active by default
        val activeJob = launch {
            delay(1000)
        }
        println(activeJob) // StandaloneCoroutine{Active}@ADD
        // here we wait until this job is done
        activeJob.join() // (1 sec)
        println(activeJob) // StandaloneCoroutine{Completed}@ADD
        // launch started lazily is in New state
        val lazyJob = launch(start = CoroutineStart.LAZY) {
            delay(1000)
        }
        println(lazyJob) // LazyStandaloneCoroutine{New}@ADD
        // we need to start it, to make it active
        lazyJob.start()
        println(lazyJob) // LazyStandaloneCoroutine{Active}@ADD
        lazyJob.join() // (1 sec)
        println(lazyJob) //LazyStandaloneCoroutine{Completed}@ADD
    }

    @Test
    fun testCoroutineCancel() {
        runBlocking {
            val job1 = launch(exceptionHandler) {
                delay(100)
                println("Running job1")
                throw CancellationException("CancellationException job1")
                println("This line will not be printed")
            }

            val job2 = launch(start = CoroutineStart.LAZY) {
                delay(200)
                println("Running job2")
            }
//            job2.start()
        }
    }

    var counter = 0

    @Test
    fun testRaceConditionCancel() {
        runBlocking {
            val job1 = launch(Dispatchers.IO) {
                withContext(NonCancellable) {
                    repeat(10) {
                        delay(200)
                        counter++
                        println("Job1 run")
                    }
                }
            }
            delay(1000)
            job1.cancelAndJoin()
            println("Counter = $counter")
        }
    }
}