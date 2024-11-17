package com.example.quicktestapplication.presentation.testflow

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.quicktestapplication.presentation.common.BaseFragment
import com.example.quicktestapplication.presentation.common.BaseFragmentContent
import com.example.quicktestapplication.presentation.common.setComposeViewContent
import kotlinx.coroutines.launch

class TestSharedFlowFragment : BaseFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BillingManager.init()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                BillingManager.sharedFlow.collect {
                    setComposeViewContent(composeView) {
                        TestSharedFlowContent(it)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TestSharedFlowContent(@PreviewParameter(ValueProvider::class) i: Int) {
    BaseFragmentContent("Test SharedFlow") {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Value: $i"
            )
        }
    }
}

class ValueProvider: PreviewParameterProvider<Int> {
    override val values: Sequence<Int>
        get() = sequenceOf(1)
}