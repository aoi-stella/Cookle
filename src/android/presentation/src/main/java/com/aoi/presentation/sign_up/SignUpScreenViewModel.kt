package com.aoi.presentation.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aoi.domain.usecase.signUp.SetStoredDataWhenSucceedSignUpUseCase
import com.aoi.domain.usecase.signUp.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * サインアップ画面のViewModel
 *
 * @param signUpUseCase サインアップに関するユースケース
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {
    private val setStoredDataWhenSucceedSignUpUseCase: SetStoredDataWhenSucceedSignUpUseCase = SetStoredDataWhenSucceedSignUpUseCase()
    // ユーザーが入力したメールアドレス
    private val _emailAddress = MutableStateFlow("")
    val emailAddress = _emailAddress.asStateFlow()

    // ユーザーが入力したパスワード
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    // ユーザーが「ログイン情報を保存する」にチェックを入れたかどうか
    private val _isRememberMeChecked = MutableStateFlow(false)
    val isRememberMeChecked = _isRememberMeChecked.asStateFlow()

    // サインアップボタンを有効にするかどうか
    private val _isSignUpButtonEnabled = MutableStateFlow(false)
    val isSignUpButtonEnabled = _isSignUpButtonEnabled.asStateFlow()

    // ユーザーが入力したメールアドレスとパスワードが有効かどうか
    private var validEmail = false
    private var validPassword = false

    // 処理中かどうか
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    // エラーダイアログを出すかどうか
    private val _showErrorDialog = MutableStateFlow(false)
    val showErrorDialog = _showErrorDialog.asStateFlow()

    // パスワードの文字数条件を満たしているかどうか
    private val _isFulFilledPasswordRequirements1 = MutableStateFlow(false)
    val isFulFilledPasswordRequirements1 = _isFulFilledPasswordRequirements1.asStateFlow()

    // パスワードの小文字条件を満たしているかどうか
    private val _isFulFilledPasswordRequirements2 = MutableStateFlow(false)
    val isFulFilledPasswordRequirements2 = _isFulFilledPasswordRequirements2.asStateFlow()

    // パスワードの大文字条件を満たしているかどうか
    private val _isFulFilledPasswordRequirements3 = MutableStateFlow(false)
    val isFulFilledPasswordRequirements3 = _isFulFilledPasswordRequirements3.asStateFlow()

    // 画面遷移のコールバック
    var onNavigate: (() -> Unit)? = null

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
        validPassword = isValidPassword(password)
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
     * サインアップボタンがクリックされたときの処理
     */
    fun onSignUpButtonClicked() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = signUpUseCase.signUp(emailAddress.value, password.value)
            if(!result.isSuccess){
                _showErrorDialog.value = true
            }
            else{
                // アカウント登録時のビジネスロジック処理を実施
                setStoredDataWhenSucceedSignUpUseCase.setStoredDataWhenSucceedSignUpUseCase(_isRememberMeChecked.value, emailAddress.value, password.value)
                onNavigate?.invoke()
            }
            _isLoading.value = false
        }
    }

    /**
     * 入力された情報が有効かどうかを検証する
     */
    private fun validateInputInfo(){
        _isSignUpButtonEnabled.value = validEmail && validPassword
    }

    /**
     * パスワードが有効かどうかを検証する
     *
     * @param password パスワード
     * @return 有効かどうか
     */
    private fun isValidPassword(password: String): Boolean {
        // 8文字以上20文字以内
        val lengthValid = password.length in 8..20
        _isFulFilledPasswordRequirements1.value = lengthValid

        // 1文字以上の小文字
        val hasLowerCase = password.any { it.isLowerCase() }
        _isFulFilledPasswordRequirements2.value = hasLowerCase

        // 1文字以上の大文字
        val hasUpperCase = password.any { it.isUpperCase() }
        _isFulFilledPasswordRequirements3.value = hasUpperCase

        return lengthValid && hasLowerCase && hasUpperCase
    }

    /**
     * ViewModelが破棄されるときの処理
     */
    override fun onCleared() {
        super.onCleared()
        onNavigate = null
    }
}