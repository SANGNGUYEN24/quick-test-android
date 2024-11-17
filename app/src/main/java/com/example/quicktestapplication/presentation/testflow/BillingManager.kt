package com.example.quicktestapplication.presentation.testflow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

object BillingManager {
    private val defaultScope = CoroutineScope(Dispatchers.Main.immediate)

    private var _sharedFlow = MutableSharedFlow<Int>(replay = 1)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun init() {
        defaultScope.launch {
            _sharedFlow.emit(24)
        }
    }
}