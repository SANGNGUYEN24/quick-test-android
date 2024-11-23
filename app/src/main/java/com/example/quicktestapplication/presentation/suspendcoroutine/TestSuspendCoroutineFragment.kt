package com.example.quicktestapplication.presentation.suspendcoroutine

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.quicktestapplication.MainViewModel
import com.example.quicktestapplication.coroutines.CoroutinePlatformTracker
import com.example.quicktestapplication.coroutines.CoroutineTracker
import com.example.quicktestapplication.presentation.common.BaseFragment
import com.example.quicktestapplication.presentation.common.BaseFragmentContent
import com.example.quicktestapplication.presentation.common.setComposeViewContent
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestSuspendCoroutineFragment: BaseFragment() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setComposeViewContent(composeView) {
            TestSuspendFragmentContent()
        }
//        lifecycleScope.launch {
//            val job = launch {
//                viewModel.fakeSuspend()
//            }
//            delay(1000)
//            job.cancel()
//        }

//        lifecycleScope.launch(CoroutineName("sang vip pro")) {
//            activity?.applicationContext?.let { viewModel.getContextSuspendFunction(it) }
//        }

        lifecycleScope.launch(CoroutinePlatformTracker()) {
            Toast.makeText(context, coroutineContext[CoroutineTracker]?.getId(), Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun TestSuspendFragmentContent() {
    BaseFragmentContent("Test suspend coroutine") {

    }
}