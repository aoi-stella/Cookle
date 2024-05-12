package com.aoi.domain.usecase.sign_in

import com.aoi.data.repository.sign_in.SignInRepository

/**
 * SignInUseCase
 *
 * サインイン画面のユースケース
 */
class SignInUseCase {
    private val repository: SignInRepository = SignInRepository()

    /**
     * サインイン
     *
     * @param email メールアドレス
     * @param password パスワード
     */
    fun signIn(email: String, password: String){
        val result = repository.signIn(email, password)
        result.addOnSuccessListener {
            println("")
        }

        result.addOnFailureListener {
            println("")
        }
    }
}