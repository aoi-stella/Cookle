package com.aoi.presentation.authentication.sign_in

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aoi.presentation.R
import com.aoi.utility.ui.dialog.CookleErrorDialog
import com.aoi.utility.ui.indicator.CookleLoadingIndicator
import com.aoi.utility.ui.user_field.UserInputField



/**
 * サインイン画面のUI
 *
 * @param onNavigate サインイン画面から他の画面に遷移するためのコールバック
 * @param vm サインイン画面のViewModel
 */
@Composable
fun SignInScreen(onNavigate: () -> Unit, vm: SignInViewModel = viewModel()){
    val isLoading by vm.isLoading.collectAsState()
    val showErrorDialog by vm.showErrorDialog.collectAsState()

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(
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
            SignInInfo(vm)
            Spacer(modifier = Modifier.height(32.dp))
            LoginButton(vm, onNavigate)
        }

        if (isLoading)
            CookleLoadingIndicator()

        if (showErrorDialog){
            CookleErrorDialog(
                onDismissRequest = { vm.onErrorDialogDismissed() },
                message = "ログインに失敗しました。\nメールアドレス及びパスワードが正しいことを確認してください。"
            )
        }
    }
}

/**
 * ログインボタン
 *
 * @param vm サインイン画面のViewModel
 * @param onNavigate サインイン画面から他の画面に遷移するためのコールバック
 */
@Composable
fun LoginButton(vm: SignInViewModel, onNavigate: () -> Unit){
    val loginButtonEnable by vm.isLoginButtonEnabled.collectAsState()
    val passLogin by vm.passLoginValidation.collectAsState()
    if (passLogin) {
        onNavigate()
    }

    Button(
        onClick = { vm.onLoginButtonClicked() },
        enabled = loginButtonEnable,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "ログイン",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * サインイン情報入力に関するUI
 *
 * @param vm サインイン画面のViewModel
 */
@Composable
fun SignInInfo(vm: SignInViewModel) {
    val emailState by vm.emailState.collectAsState()
    val passwordState by vm.passwordState.collectAsState()
    val isRememberMeChecked by vm.isRememberMeChecked.collectAsState()

    // メールアドレス入力欄
    UserInputField(
        "メールアドレス",
        emailState,
        R.drawable.ic_email
    ) { vm.onEmailChanged(it) }
    Spacer(modifier = Modifier.height(16.dp))
    // パスワード入力欄
    UserInputField(
        "パスワード",
        passwordState,
        R.drawable.ic_key
    ) { vm.onPasswordChanged(it) }
    Spacer(modifier = Modifier.height(16.dp))
    // サインイン情報記憶チェックボックス
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Checkbox(
            checked = isRememberMeChecked,
            onCheckedChange = { vm.onRememberMeCheckedChanged(it) }
        )
        Spacer(Modifier.size(6.dp))
        Text(
            text = "ログイン情報を保存する"
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
    SignInScreen({})
}