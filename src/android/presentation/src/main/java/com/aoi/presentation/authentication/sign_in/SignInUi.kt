package com.aoi.presentation.authentication.sign_in

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aoi.presentation.R

/**
 * サインイン画面のUI
 *
 * @param onNavigate サインイン画面から他の画面に遷移するためのコールバック
 */
@Composable
fun SignInScreen(onNavigate: () -> Unit) {
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
            text = "こんにちは!\uD83D\uDC4B",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "またお会いしましたね！",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(50.dp))
        SignInInfo()
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
                text = "ログイン",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * サインイン情報入力に関するUI
 */
@Composable
fun SignInInfo() {
    // メールアドレス入力欄
    UserInputField(
        "",
        R.drawable.ic_email
    ) {}
    Spacer(modifier = Modifier.height(16.dp))
    // パスワード入力欄
    UserInputField(
        "",
        R.drawable.ic_key
    ) {}
    Spacer(modifier = Modifier.height(16.dp))
    // サインイン情報記憶チェックボックス
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        Checkbox(
            checked = true,
            onCheckedChange = null
        )
        Spacer(Modifier.size(6.dp))
        Text(
            text = "ログイン情報を保存する",
        )
    }
}

/**
 * ユーザー入力欄
 * @param value 入力された値
 * @param leadingIconId 入力欄の先頭に表示するアイコンのリソースID
 * @param onValueChange 入力された値が変更されたときのコールバック
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputField(
    value: String,
    leadingIconId: Int,
    onValueChange: (String) -> Unit
) {
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            value = value,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = { onValueChange(it) },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(leadingIconId),
                    contentDescription = "Icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}

/**
 * サインイン画面のプレビュー
 */
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun SignInScreenPreview() {
    SignInScreen {}
}