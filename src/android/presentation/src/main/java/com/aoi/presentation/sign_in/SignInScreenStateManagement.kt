package com.aoi.presentation.sign_in

/**
 * サインイン画面の状態
 *
 * @param isLoading 処理中かどうか
 * @param showErrorDialog エラーダイアログを表示するかどうか
 * @param emailAddress ユーザーが入力したメールアドレス
 * @param password ユーザーが入力したパスワード
 * @param isRememberMeChecked ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
 * @param isLoginButtonEnabled ログインボタンを有効にするかどうか
 */
data class SignInScreenState(
    val isLoading: Boolean,
    val showErrorDialog: Boolean,
    val emailAddress: String,
    val password: String,
    val isRememberMeChecked: Boolean,
    val isLoginButtonEnabled: Boolean
)

/**
 * サインイン画面のイベント
 *
 * @param onEmailChanged ユーザーが入力したメールアドレスが変更されたときの処理
 * @param onPasswordChanged ユーザーが入力したパスワードが変更されたときの処理
 * @param onRememberMeCheckedChanged ユーザーが「ログイン情報を保存する」にチェックを入れたかどうかが変更されたときの処理
 * @param onLoginButtonClicked ログインボタンがクリックされたときの処理
 * @param onErrorDialogDismissed エラーダイアログが閉じられたときの処理
 */
data class SignInScreenEvent(
    val onEmailChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
    val onRememberMeCheckedChanged: (Boolean) -> Unit,
    val onLoginButtonClicked: () -> Unit,
    val onErrorDialogDismissed: () -> Unit
)