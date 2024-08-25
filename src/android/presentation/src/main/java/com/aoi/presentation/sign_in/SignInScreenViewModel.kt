package com.aoi.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aoi.domain.usecase.authentication.SignInUseCase
import com.aoi.domain.usecase.getStoredUserInformation.GetUserLoginInformationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * サインイン画面のViewModel
 *
 * @param signInUseCase サインインに関するユースケース
 */
class SignInViewModel(
    private val signInUseCase: SignInUseCase = SignInUseCase(),
    private val getUserLoginInformationUseCase: GetUserLoginInformationUseCase = GetUserLoginInformationUseCase()
): ViewModel() {
    // ユーザーが入力したメールアドレス
    private val _emailAddress = MutableStateFlow("")
    val emailAddress = _emailAddress.asStateFlow()

    // ユーザーが入力したパスワード
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    // ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
    private val _isRememberMeChecked = MutableStateFlow(false)
    val isRememberMeChecked = _isRememberMeChecked.asStateFlow()

    // ログインボタンを有効にするかどうか
    private val _isLoginButtonEnabled = MutableStateFlow(false)
    val isLoginButtonEnabled = _isLoginButtonEnabled.asStateFlow()

    // ユーザーが入力したメールアドレスとパスワードが有効かどうか
    private var validEmail = false
    private var validPassword = false

    // 処理中かどうか
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // エラーダイアログを出すかどうか
    private val _showErrorDialog = MutableStateFlow(false)
    val showErrorDialog = _showErrorDialog.asStateFlow()

    // 画面遷移のコールバック
    var onNavigate: (() -> Unit)? = null

    init {
        setPreviousUserLoginInformation()
    }

    /**
     * ユーザーが入力したメールアドレスが変更されたときの処理
     *
     * @param email ユーザーが入力したメールアドレス
     */
    fun onEmailChanged(email: String) {
        _emailAddress.value = email
        validEmail = email.contains("@")
        validateInputInfo()
    }

    /**
     * ユーザーが入力したパスワードが変更されたときの処理
     *
     * @param password ユーザーが入力したパスワード
     */
    fun onPasswordChanged(password: String) {
        _password.value = password
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
     * エラーダイアログが閉じられたときの処理
     */
    fun onErrorDialogDismissed() {
        _showErrorDialog.value = false
    }

    /**
     * ログインボタンがクリックされたときの処理
     */
    fun onLoginButtonClicked() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = signInUseCase.signIn(emailAddress.value, password.value)
            if(!result.isSuccess){
                _showErrorDialog.value = true
            }
            else{
                onNavigate?.invoke()
            }
            _isLoading.value = false
        }
    }

    /**
     * 入力された情報が有効かどうかを検証する
     */
    private fun validateInputInfo(){
        _isLoginButtonEnabled.value = validEmail && validPassword
    }

    /**
     * setPreviousUserLoginInformation
     *
     * 以前にログイン情報を保存していた場合、その情報をセットする
     */
    private fun setPreviousUserLoginInformation(){
        val userLoginInformation = getUserLoginInformationUseCase.getUserLoginInformation()
        val isEmptyInformation = userLoginInformation.first.isEmpty() && userLoginInformation.second.isEmpty()
        if(isEmptyInformation){
            return
        }

        _emailAddress.value = userLoginInformation.first
        _password.value = userLoginInformation.second
    }

    /**
     * ViewModelが破棄されるときの処理
     */
    override fun onCleared() {
        super.onCleared()
        onNavigate = null
    }
}