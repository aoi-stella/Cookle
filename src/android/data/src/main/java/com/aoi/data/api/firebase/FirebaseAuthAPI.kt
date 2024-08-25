package com.aoi.data.api.firebase

import com.aoi.core.firebase.FirebaseAuthManager
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

/**
 * FirebaseAuthAPI
 *
 * Firebase AuthenticationのAPIを提供します。
 */
object FirebaseAuthAPI{
    private val auth = FirebaseAuthManager.auth!!

    /**
     * signInWithEmailAndPassword
     *
     * メールアドレスとパスワードでサインインします。
     *
     * @param email メールアドレス
     * @param password パスワード
     * @return Task<AuthResult>
     */
    fun signInWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }

    /**
     * signUpWithEmailAndPassword
     *
     * メールアドレスとパスワードでサインアップします。
     *
     * @param email メールアドレス
     * @param password パスワード
     * @return Task<AuthResult>
     */
    fun signUpWithEmailAndPassword(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }
}