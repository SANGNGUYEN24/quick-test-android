package com.example.quicktestapplication.presentation

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.fragment.app.FragmentManager
import com.example.quicktestapplication.presentation.common.BaseFragment
import com.example.quicktestapplication.presentation.common.BaseFragmentContent
import com.example.quicktestapplication.presentation.common.setComposeViewContent
import com.example.quicktestapplication.R
import com.example.quicktestapplication.presentation.suspendcoroutine.TestSuspendCoroutineFragment
import com.example.quicktestapplication.presentation.testflow.TestSharedFlowFragment

class MainFragment : BaseFragment() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setComposeViewContent(composeView) {
            MainFragmentContent(parentFragmentManager)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainFragmentContent(@PreviewParameter(FragmentManagerProvider::class) fragmentManager: FragmentManager) {
    BaseFragmentContent("Sang vip pro") { padding ->
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = padding.calculateTopPadding())
        ) {
            Button(
                onClick = {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        TestSharedFlowFragment()).addToBackStack("MainFragment").commit()
                }
            ) {
                Text(text = "Test SharedFlow")
            }

            Button(
                onClick = {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,
                        TestSuspendCoroutineFragment()).addToBackStack("TestSuspendCoroutine").commit()
                }
            ) {
                Text(text = "Test Suspend Coroutine")
            }
        }
    }
}



class FragmentManagerProvider : PreviewParameterProvider<FragmentManager> {
    override val values: Sequence<FragmentManager>
        get() = sequenceOf(object : FragmentManager() {})
}