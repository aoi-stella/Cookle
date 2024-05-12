package com.aoi.data.repository.sign_in

import com.aoi.data.api.firebase.FirebaseAuthAPI
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

/**
 * SignInRepository
 *
 * サインイン画面に関するリポジトリです。
 */
class SignInRepository {
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
}