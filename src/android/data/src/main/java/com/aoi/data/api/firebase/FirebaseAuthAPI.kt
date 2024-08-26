package com.aoi.data.api.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

/**
 * FirebaseAuthAPI
 *
 * Firebase AuthenticationのAPIを提供します。
 */
class FirebaseAuthAPI @Inject constructor(
    private val firebaseAuth: FirebaseAuth
){

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
        return firebaseAuth.signInWithEmailAndPassword(email, password)
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
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }
}