package com.example.quicktestapplication.presentation.common

import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.style.TextOverflow
import com.example.quicktestapplication.ui.theme.QuickTestApplicationTheme

fun setComposeViewContent(composeView: ComposeView?, content: @Composable () -> Unit) {
    composeView?.apply {
        // Dispose of the Composition when the view's LifecycleOwner is destroyed
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent { content() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                maxLines = 1,
                text = title,
                overflow = TextOverflow.Ellipsis
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun BaseFragmentContent(actionBarTitle: String, content: @Composable (paddingValues: PaddingValues) -> Unit) {
    QuickTestApplicationTheme {
        Scaffold(
            topBar = {
                MyTopAppBar(actionBarTitle)
            }
        ) { padding ->
            content(padding)
        }
    }
}
