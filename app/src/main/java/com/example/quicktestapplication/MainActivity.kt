package com.example.quicktestapplication

import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.quicktestapplication.presentation.MainFragment
import com.example.quicktestapplication.utils.ToastWrapper.showToast
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_layout)
        supportFragmentManager.beginTransaction().replace(
            R.id.fragment_container,
            MainFragment()
        ).commit()

        lifecycleScope.launch {
            if (intent.hasExtra("source")) {
                showToast(this@MainActivity, "Open app from ${intent.getStringExtra("source")}")
            }
        }
    }
}
