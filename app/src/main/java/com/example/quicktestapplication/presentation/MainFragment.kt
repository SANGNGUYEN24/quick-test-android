package com.example.quicktestapplication.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.example.quicktestapplication.presentation.common.BaseFragment
import com.example.quicktestapplication.presentation.common.BaseFragmentContent
import com.example.quicktestapplication.presentation.common.setComposeViewContent
import com.example.quicktestapplication.R
import com.example.quicktestapplication.pdfrender.PDFFragment
import com.example.quicktestapplication.presentation.suspendcoroutine.TestSuspendCoroutineFragment
import com.example.quicktestapplication.presentation.testflow.TestSharedFlowFragment
import com.example.quicktestapplication.shortcut.ShortcutFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : BaseFragment() {
    @RequiresApi(Build.VERSION_CODES.N_MR1)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setComposeViewContent(composeView) {
            MainFragmentContent(parentFragmentManager, activity, lifecycleScope)
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainFragmentContent(
    @PreviewParameter(FragmentManagerProvider::class) fragmentManager: FragmentManager,
    activity: FragmentActivity?,
    lifecycleScope: LifecycleCoroutineScope
) {
    val context = LocalContext.current
    BaseFragmentContent("Sang vip pro") { topPadding ->
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPadding)
        ) {
            Button(
                onClick = {
                    fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        TestSharedFlowFragment()
                    ).addToBackStack("MainFragment").commit()
                }
            ) {
                Text(text = "Test SharedFlow")
            }

            Button(
                onClick = {
                    fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        TestSuspendCoroutineFragment()
                    ).addToBackStack("TestSuspendCoroutine").commit()
                }
            ) {
                Text(text = "Test Suspend Coroutine")
            }

            Button(
                onClick = {
                    fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        PDFFragment()
                    ).addToBackStack("PDFFragment").commit()
                }
            ) {
                Text(text = "Test Render PDF")
            }

            Button(
                onClick = {
                    fragmentManager.beginTransaction().replace(
                        R.id.fragment_container,
                        ShortcutFragment()
                    ).addToBackStack("ShortcutFragment").commit()
                }
            ) {
                Text(text = "Test shortcut")
            }
        }
    }
}


class FragmentManagerProvider : PreviewParameterProvider<FragmentManager> {
    override val values: Sequence<FragmentManager>
        get() = sequenceOf(object : FragmentManager() {})
}

class ActivityProvider : PreviewParameterProvider<Activity> {
    override val values: Sequence<Activity>
        get() = sequenceOf(object : Activity() {})
}