package com.aoi.presentation.authentication.sign_in

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * サインイン画面のViewModel
 */
class SignInViewModel: ViewModel() {
    // ユーザーが入力したメールアドレス
    private val _emailState = MutableStateFlow("")
    val emailState = _emailState.asStateFlow()

    // ユーザーが入力したパスワード
    private val _passwordState = MutableStateFlow("")
    val passwordState = _passwordState.asStateFlow()

    // ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
    private val _isRememberMeChecked = MutableStateFlow(false)
    val isRememberMeChecked = _isRememberMeChecked.asStateFlow()

    /**
     * ユーザーが入力したメールアドレスが変更されたときの処理
     *
     * @param email ユーザーが入力したメールアドレス
     */
    fun onEmailChanged(email: String) {
        _emailState.value = email
    }

    /**
     * ユーザーが入力したパスワードが変更されたときの処理
     *
     * @param password ユーザーが入力したパスワード
     */
    fun onPasswordChanged(password: String) {
        _passwordState.value = password
    }

    /**
     * ユーザーが「ログイン情報を保存する」にチェックを入れたかどうかが変更されたときの処理
     *
     * @param isChecked チェックボックスの状態
     */
    fun onRememberMeCheckedChanged(isChecked: Boolean) {
        _isRememberMeChecked.value = isChecked
    }
}