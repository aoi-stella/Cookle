package com.aoi.presentation.sign_up

/**
 * サインアップ画面の状態
 *
 * @param isLoading 処理中かどうか
 * @param showErrorDialog エラーダイアログを表示するかどうか
 * @param emailAddress ユーザーが入力したメールアドレス
 * @param password ユーザーが入力したパスワード
 * @param isRememberMeChecked ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
 * @param isSignUpButtonEnabled ログインボタンを有効にするかどうか
 * @param isFulFilledPasswordRequirements1 パスワードの条件1(文字数制限)を満たしているかどうか
 * @param isFulFilledPasswordRequirements2 パスワードの条件2(小文字を含む)を満たしているかどうか
 * @param isFulFilledPasswordRequirements3 パスワードの条件3(大文字を含む)を満たしているかどうか
 */
data class SignUpScreenState(
    val isLoading: Boolean,
    val showErrorDialog: Boolean,
    val emailAddress: String,
    val password: String,
    val isRememberMeChecked: Boolean,
    val isSignUpButtonEnabled: Boolean,
    val isFulFilledPasswordRequirements1: Boolean,
    val isFulFilledPasswordRequirements2: Boolean,
    val isFulFilledPasswordRequirements3: Boolean,
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