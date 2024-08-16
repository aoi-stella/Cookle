package com.aoi.presentation.authentication.sign_in

import android.content.res.Configuration
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aoi.presentation.R
import com.aoi.utility.ui.dialog.CookleErrorDialog
import com.aoi.utility.ui.indicator.CookleLoadingIndicator
import com.aoi.utility.ui.user_field.CookleUserInputField


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
    val emailAddress by vm.emailAddress.collectAsState()
    val password by vm.password.collectAsState()
    val isRememberMeChecked by vm.isRememberMeChecked.collectAsState()
    val isLoginButtonEnable by vm.isLoginButtonEnabled.collectAsState()

    LaunchedEffect(Unit){
        vm.onNavigate = onNavigate
    }

    Scaffold(
        topBar = { Header() },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignInInfo(
                        emailAddress,
                        password,
                        isRememberMeChecked,
                        { vm.onEmailChanged(it) },
                        { vm.onPasswordChanged(it) },
                        { vm.onRememberMeCheckedChanged(it) })
                    Spacer(modifier = Modifier.height(32.dp))
                    LoginButton(
                        isLoginButtonEnabled = isLoginButtonEnable,
                        onLoginButtonClicked = { vm.onLoginButtonClicked() }
                    )
                }

                if (isLoading)
                    CookleLoadingIndicator()

                if (showErrorDialog) {
                    CookleErrorDialog(
                        onDismissRequest = { vm.onErrorDialogDismissed() },
                        message = "ログインに失敗しました。\nメールアドレス及びパスワードが正しいことを確認してください。"
                    )
                }
            }
        }
    )
}

/**
 * ログインボタン
 *
 * @param isLoginButtonEnabled ログインボタンが有効かどうか
 * @param onLoginButtonClicked ログインボタンがクリックされた時のコールバック
 */
@Composable
fun LoginButton(
    isLoginButtonEnabled: Boolean,
    onLoginButtonClicked: () -> Unit){

    Button(
        onClick = { onLoginButtonClicked() },
        enabled = isLoginButtonEnabled,
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
 * @param emailAddress メールアドレス
 * @param password パスワード
 * @param isRememberMeChecked ログイン情報を保存するかどうか
 * @param onEmailChanged メールアドレスが変更された時のコールバック
 * @param onPasswordChanged パスワードが変更された時のコールバック
 * @param onRememberMeCheckedChanged ログイン情報を保存するかどうかが変更された時のコールバック
 */
@Composable
fun SignInInfo(
    emailAddress: String,
    password: String,
    isRememberMeChecked: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRememberMeCheckedChanged: (Boolean) -> Unit){
    // メールアドレス入力欄
    CookleUserInputField(
        "メールアドレス",
        emailAddress,
        R.drawable.ic_email,
        { onEmailChanged(it) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    // パスワード入力欄
    CookleUserInputField(
        "パスワード",
        password,
        R.drawable.ic_key,
        { onPasswordChanged(it) },
        true
    )
    Spacer(modifier = Modifier.height(16.dp))
    // サインイン情報記憶チェックボックス
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Checkbox(
            checked = isRememberMeChecked,
            onCheckedChange = { onRememberMeCheckedChanged(it) }
        )
        Spacer(Modifier.size(6.dp))
        Text(
            text = "ログイン情報を保存する"
        )
    }
}

/**
 * ヘッダー
 */
@Composable
fun Header(){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 36.dp, end = 12.dp, bottom = 12.dp)
        ) {
            Text(
                text = "会員ログイン",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
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