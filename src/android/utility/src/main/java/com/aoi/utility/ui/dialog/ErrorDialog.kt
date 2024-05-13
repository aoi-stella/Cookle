package com.aoi.utility.ui.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun CookleErrorDialog(
    onDismissRequest: () -> Unit,
    title: String = "エラー",
    message: String = "エラーが発生しました"
){
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(title) },
        text = { Text(message) },
        confirmButton = {
            TextButton(onClick = { onDismissRequest() }) {
                Text("OK")
            }
        }
    )
}