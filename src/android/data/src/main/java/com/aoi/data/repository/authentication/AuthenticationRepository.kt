package com.aoi.data.repository.authentication

import com.aoi.data.api.firebase.FirebaseAuthAPI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

/**
 * AuthenticationRepository
 *
 * 認証に関するリポジトリです。
 */
class AuthenticationRepository {
    /**
     * signIn
     *
     * メールアドレスとパスワードでサインインします。
     *
     * @param email メールアドレス
     * @param password パスワード
     * @return Task<AuthResult>
     */
    fun signIn(email: String, password: String): Task<AuthResult> {
        return FirebaseAuthAPI.signInWithEmailAndPassword(email, password)
    }

    /**
     * signUp
     *
     * メールアドレスとパスワードでサインアップします。
     *
     * @param email メールアドレス
     * @param password パスワード
     * @return Task<AuthResult>
     */
    fun signUp(email: String, password: String): Task<AuthResult> {
        return FirebaseAuthAPI.signUpWithEmailAndPassword(email, password)
    }
}