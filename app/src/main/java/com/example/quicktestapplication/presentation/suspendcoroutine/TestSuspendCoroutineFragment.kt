package com.example.quicktestapplication.presentation.suspendcoroutine

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.quicktestapplication.MainViewModel
import com.example.quicktestapplication.presentation.common.BaseFragment
import com.example.quicktestapplication.presentation.common.BaseFragmentContent
import com.example.quicktestapplication.presentation.common.setComposeViewContent
import kotlinx.coroutines.launch

class TestSuspendCoroutineFragment: BaseFragment() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setComposeViewContent(composeView) {
            TestSuspendFragmentContent()
        }
        lifecycleScope.launch {
            viewModel.fakeSuspend()
        }
    }
}

@Composable
fun TestSuspendFragmentContent() {
    BaseFragmentContent("Test suspend coroutine") {

    }
}