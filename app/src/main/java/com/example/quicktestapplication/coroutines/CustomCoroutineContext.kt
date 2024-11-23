package com.example.quicktestapplication.coroutines

import kotlin.coroutines.CoroutineContext

abstract class CoroutineTracker: CoroutineContext.Element {
    override val key: CoroutineContext.Key<*> = Key

    companion object Key: CoroutineContext.Key<CoroutineTracker>

    abstract fun getId(): String
}

class CoroutinePlatformTracker: CoroutineTracker() {
    override fun getId(): String {
        return "Platform"
    }
}
