package com.example.quicktestapplication.pdfrender.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicktestapplication.ui.theme.QuickTestApplicationTheme

@Preview(showSystemUi = true)
@Composable
fun PDFDevTool() {
    val url = remember { mutableStateOf("") }
    QuickTestApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Column {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = url.value,
                    onValueChange = {
                        url.value = it
                    },
                    label = {
                        Text(text = "Fill a PDF URL", fontSize = 12.sp)
                    }
                )

                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = {}
                ) {
                    Text("Show PDF")
                }
            }
        }
    }
}