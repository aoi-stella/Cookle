package com.aoi.presentation.authentication.sign_up

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aoi.presentation.R
import com.aoi.utility.ui.user_field.CookleUserInputField

/**
 * サインアップ画面のUI
 * @param onNavigate サインイン画面から他の画面に遷移するためのコールバック
 */
@Composable
fun SignUpScreen(onNavigate: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_app_image),
            contentDescription = "My Image",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "ようこそ!\uD83D\uDC4B",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "まずはアカウント作成をしましょう！",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(50.dp))
        SignUpInfo()
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { onNavigate() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "アカウントを作成する",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * サインアップ画面の情報入力欄
 */
@Composable
fun SignUpInfo(){
    // メールアドレス入力欄
    CookleUserInputField(
        "メールアドレスの入力",
        "",
        R.drawable.ic_email,
        {}
    )
    Spacer(modifier = Modifier.height(16.dp))
    // パスワード入力欄
    CookleUserInputField(
        "パスワードの入力",
        "",
        R.drawable.ic_key,
        {},
        true
    )
    Spacer(modifier = Modifier.height(16.dp))
    // パスワード再入力欄
    CookleUserInputField(
        "パスワードの再確認",
        "",
        R.drawable.ic_key,
        {},
        true
    )
}

/**
 * サインアップ画面のプレビュー
 */
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun SignUpScreenPreview(){
    SignUpScreen{}
}