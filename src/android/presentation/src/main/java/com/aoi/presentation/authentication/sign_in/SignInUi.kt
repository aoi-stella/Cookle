package com.aoi.presentation.authentication.sign_in

import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * サインイン画面のUI
 *
 * @param onNavigate サインイン画面から他の画面に遷移するためのコールバック
 */
@Composable
fun SignInScreen(onNavigate: () -> Unit) {
    Button(onClick = { onNavigate() }) {
        
    }
}

/**
 * サインイン画面のプレビュー
 */
@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen {}
}