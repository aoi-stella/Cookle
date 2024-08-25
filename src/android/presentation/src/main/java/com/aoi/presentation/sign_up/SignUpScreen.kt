package com.aoi.presentation.sign_up

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aoi.presentation.R
import com.aoi.utility.ui.dialog.CookleErrorDialog
import com.aoi.utility.ui.indicator.CookleLoadingIndicator
import com.aoi.utility.ui.user_field.CookleUserInputField

/**
 * サインアップ画面
 *
 * @param onNavigate 画面遷移のコールバック
 * @param vm サインアップ画面のViewModel
 */
@Composable
fun SignUpScreen(onNavigate: () -> Unit, vm: SignUpViewModel = viewModel()){
    val isLoading by vm.isLoading.collectAsState()
    val showErrorDialog by vm.showErrorDialog.collectAsState()
    val emailAddress by vm.emailAddress.collectAsState()
    val password by vm.password.collectAsState()
    val isRememberMeChecked by vm.isRememberMeChecked.collectAsState()
    val isSignUpButtonEnable by vm.isSignUpButtonEnabled.collectAsState()
    val isFulFilledPasswordRequirements1 by vm.isFulFilledPasswordRequirements1.collectAsState()
    val isFulFilledPasswordRequirements2 by vm.isFulFilledPasswordRequirements2.collectAsState()
    val isFulFilledPasswordRequirements3 by vm.isFulFilledPasswordRequirements3.collectAsState()
    val signUpScreenState = SignUpScreenState(
        isLoading,
        showErrorDialog,
        emailAddress,
        password,
        isRememberMeChecked,
        isSignUpButtonEnable,
        isFulFilledPasswordRequirements1,
        isFulFilledPasswordRequirements2,
        isFulFilledPasswordRequirements3,
    )

    val signUpScreenEvent = SignUpScreenEvent(
        { vm.onEmailChanged(it) },
        { vm.onPasswordChanged(it) },
        { vm.onRememberMeCheckedChanged(it) },
        { vm.onSignUpButtonClicked() },
        { vm.onErrorDialogDismissed() }
    )
    LaunchedEffect(Unit){
        vm.onNavigate = onNavigate
    }

    SignUpScreen(signUpScreenState, signUpScreenEvent)
}
/**
 * サインアップ画面のUI
 *
 * @param signUpScreenState サインアップ画面の状態
 * @param signUpScreenEvent サインアップ画面のイベント処理
 */
@Composable
fun SignUpScreen(signUpScreenState: SignUpScreenState,signUpScreenEvent: SignUpScreenEvent){
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
                    SignUpInfo(
                        emailAddress =  signUpScreenState.emailAddress,
                        password =  signUpScreenState.password,
                        isRememberMeChecked =  signUpScreenState.isRememberMeChecked,
                        onEmailChanged =  { signUpScreenEvent.onEmailChanged(it) },
                        onPasswordChanged =  { signUpScreenEvent.onPasswordChanged(it) },
                        onRememberMeCheckedChanged =  { signUpScreenEvent.onRememberMeCheckedChanged(it)},
                        isFulFilledPasswordRequirements1 = signUpScreenState.isFulFilledPasswordRequirements1,
                        isFulFilledPasswordRequirements2 = signUpScreenState.isFulFilledPasswordRequirements2,
                        isFulFilledPasswordRequirements3 = signUpScreenState.isFulFilledPasswordRequirements3)
                    Spacer(modifier = Modifier.height(32.dp))
                    SignUpButton(
                        isSignUpButtonEnabled = signUpScreenState.isSignUpButtonEnabled,
                        onSignUpButtonClicked = { signUpScreenEvent.onSignUpButtonClicked() }
                    )
                }

                if (signUpScreenState.isLoading)
                    CookleLoadingIndicator()

                if (signUpScreenState.showErrorDialog) {
                    CookleErrorDialog(
                        onDismissRequest = { signUpScreenEvent.onErrorDialogDismissed() },
                        message = stringResource(id = R.string.sign_up_dialog_failed_to_sign_up)
                    )
                }
            }
        }
    )
}

/**
 * サインアップボタン
 *
 * @param isSignUpButtonEnabled サインアップボタンが有効かどうか
 * @param onSignUpButtonClicked サインアップボタンがクリックされた時のコールバック
 */
@Composable
fun SignUpButton(
    isSignUpButtonEnabled: Boolean,
    onSignUpButtonClicked: () -> Unit){

    Button(
        onClick = { onSignUpButtonClicked() },
        enabled = isSignUpButtonEnabled,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_text_sign_up),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * サインアップ情報入力に関するUI
 *
 * @param emailAddress メールアドレス
 * @param password パスワード
 * @param isRememberMeChecked ログイン情報を保存するかどうか
 * @param isFulFilledPasswordRequirements1 パスワードの条件1(文字数制限)を満たしているかどうか
 * @param isFulFilledPasswordRequirements2 パスワードの条件2(小文字を含む)を満たしているかどうか
 * @param isFulFilledPasswordRequirements3 パスワードの条件3(大文字を含む)を満たしているかどうか
 * @param onEmailChanged メールアドレスが変更された時のコールバック
 * @param onPasswordChanged パスワードが変更された時のコールバック
 * @param onRememberMeCheckedChanged ログイン情報を保存するかどうかが変更された時のコールバック
 */
@Composable
fun SignUpInfo(
    emailAddress: String,
    password: String,
    isRememberMeChecked: Boolean,
    isFulFilledPasswordRequirements1: Boolean,
    isFulFilledPasswordRequirements2: Boolean,
    isFulFilledPasswordRequirements3: Boolean,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRememberMeCheckedChanged: (Boolean) -> Unit){
    // メールアドレス入力欄
    CookleUserInputField(
        stringResource(id = R.string.sign_up_outlined_text_field_mail_address),
        emailAddress,
        R.drawable.ic_email,
        { onEmailChanged(it) }
    )
    Spacer(modifier = Modifier.height(16.dp))
    // パスワード入力欄
    CookleUserInputField(
        stringResource(id = R.string.sign_up_outlined_text_field_password),
        password,
        R.drawable.ic_key,
        { onPasswordChanged(it) },
        true
    )
    Spacer(modifier = Modifier.height(16.dp))
    PasswordCautions(isFulFilledPasswordRequirements1, isFulFilledPasswordRequirements2, isFulFilledPasswordRequirements3)
    // サインアップ情報記憶チェックボックス
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
            text = stringResource(id = R.string.sign_up_checkbox_save_login_information)
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
                text = stringResource(id = R.string.sign_up_text_header),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * PasswordCautions
 *
 * パスワードの注意事項を表示する
 *
 * @param isFulFilledPasswordRequirements1 パスワードの条件1(文字数制限)を満たしているかどうか
 * @param isFulFilledPasswordRequirements2 パスワードの条件2(小文字を含む)を満たしているかどうか
 * @param isFulFilledPasswordRequirements3 パスワードの条件3(大文字を含む)を満たしているかどうか
 */
@Composable
fun PasswordCautions(isFulFilledPasswordRequirements1: Boolean, isFulFilledPasswordRequirements2: Boolean, isFulFilledPasswordRequirements3: Boolean){
    val caution1Color = if(isFulFilledPasswordRequirements1){
        MaterialTheme.colorScheme.primary
    } else{
        MaterialTheme.colorScheme.error
    }

    val caution2Color = if(isFulFilledPasswordRequirements2){
        MaterialTheme.colorScheme.primary
    } else{
        MaterialTheme.colorScheme.error
    }

    val caution3Color = if(isFulFilledPasswordRequirements3){
        MaterialTheme.colorScheme.primary
    } else{
        MaterialTheme.colorScheme.error
    }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_text_password_caution_1),
            style = MaterialTheme.typography.bodySmall,
            color = caution1Color
        )
        Text(
            text = stringResource(id = R.string.sign_up_text_password_caution_2),
            style = MaterialTheme.typography.bodySmall,
            color = caution2Color
        )
        Text(
            text = stringResource(id = R.string.sign_up_text_password_caution_3),
            style = MaterialTheme.typography.bodySmall,
            color = caution3Color
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.sign_up_text_password_caution_4),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/**
 * サインアップ画面のプレビュー
 */
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    apiLevel = 33
)
@Composable
fun SignUpScreenPreview() {
    val signUpScreenState = SignUpScreenState(
        isLoading = false,
        showErrorDialog = false,
        emailAddress =  "",
        password = "",
        isRememberMeChecked = false,
        isSignUpButtonEnabled = false,
        isFulFilledPasswordRequirements1 = true,
        isFulFilledPasswordRequirements2 = false,
        isFulFilledPasswordRequirements3 = false
    )
    val signUpScreenEvent = SignUpScreenEvent(
        onEmailChanged = {},
        onPasswordChanged = {},
        onRememberMeCheckedChanged = {},
        onSignUpButtonClicked = {},
        onErrorDialogDismissed = {}
    )
    SignUpScreen(signUpScreenState, signUpScreenEvent)
}