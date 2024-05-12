package com.aoi.presentation.authentication.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aoi.domain.usecase.sign_in.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * サインイン画面のViewModel
 *
 * @param signInUseCase サインインに関するユースケース
 */
class SignInViewModel(
    private val signInUseCase: SignInUseCase = SignInUseCase()
): ViewModel() {
    // ユーザーが入力したメールアドレス
    private val _emailState = MutableStateFlow("")
    val emailState = _emailState.asStateFlow()

    // ユーザーが入力したパスワード
    private val _passwordState = MutableStateFlow("")
    val passwordState = _passwordState.asStateFlow()

    // ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
    private val _isRememberMeChecked = MutableStateFlow(false)
    val isRememberMeChecked = _isRememberMeChecked.asStateFlow()

    // ログインボタンを有効にするかどうか
    private val _isLoginButtonEnabled = MutableStateFlow(false)
    val isLoginButtonEnabled = _isLoginButtonEnabled.asStateFlow()

    // ログイン情報のバリデーションが通ったかどうか
    private val _passLoginValidation = MutableStateFlow(false)
    val passLoginValidation = _passLoginValidation.asStateFlow()

    // ユーザーが入力したメールアドレスとパスワードが有効かどうか
    private var validEmail = false
    private var validPassword = false

    /**
     * ユーザーが入力したメールアドレスが変更されたときの処理
     *
     * @param email ユーザーが入力したメールアドレス
     */
    fun onEmailChanged(email: String) {
        _emailState.value = email
        validEmail = email.contains("@")
        validateInputInfo()
    }

    /**
     * ユーザーが入力したパスワードが変更されたときの処理
     *
     * @param password ユーザーが入力したパスワード
     */
    fun onPasswordChanged(password: String) {
        _passwordState.value = password
        validPassword = password.isNotEmpty()
        validateInputInfo()
    }

    /**
     * ユーザーが「ログイン情報を保存する」にチェックを入れたかどうかが変更されたときの処理
     *
     * @param isChecked チェックボックスの状態
     */
    fun onRememberMeCheckedChanged(isChecked: Boolean) {
        _isRememberMeChecked.value = isChecked
    }

    /**
     * ログインボタンがクリックされたときの処理
     */
    fun onLoginButtonClicked() {
        viewModelScope.launch {
            signInUseCase.signIn(emailState.value, passwordState.value)
            //_passLoginValidation.emit(true)
        }
    }

    /**
     * 入力された情報が有効かどうかを検証する
     */
    private fun validateInputInfo(){
        _isLoginButtonEnabled.value = validEmail && validPassword
    }
}