package com.aoi.presentation.sign_up

/**
 * サインアップ画面の状態
 *
 * @param isLoading 処理中かどうか
 * @param showErrorDialog エラーダイアログを表示するかどうか
 * @param emailAddress ユーザーが入力したメールアドレス
 * @param password ユーザーが入力したパスワード
 * @param isRememberMeChecked ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
 * @param isLoginButtonEnabled ログインボタンを有効にするかどうか
 */
data class SignUpScreenState(
    val isLoading: Boolean,
    val showErrorDialog: Boolean,
    val emailAddress: String,
    val password: String,
    val isRememberMeChecked: Boolean,
    val isLoginButtonEnabled: Boolean
)

/**
 * サインアップ画面のイベント
 *
 * @param onEmailChanged ユーザーが入力したメールアドレスが変更されたときの処理
 * @param onPasswordChanged ユーザーが入力したパスワードが変更されたときの処理
 * @param onRememberMeCheckedChanged ユーザーが「ログイン情報を保存する」にチェックを入れたかどうかが変更されたときの処理
 * @param onSignUpButtonClicked サインアップボタンがクリックされたときの処理
 * @param onErrorDialogDismissed エラーダイアログが閉じられたときの処理
 */
data class SignUpScreenEvent(
    val onEmailChanged: (String) -> Unit,
    val onPasswordChanged: (String) -> Unit,
    val onRememberMeCheckedChanged: (Boolean) -> Unit,
    val onSignUpButtonClicked: () -> Unit,
    val onErrorDialogDismissed: () -> Unit
)