package com.aoi.domain.usecase.sign_in

import com.aoi.data.repository.sign_in.SignInRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

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
    suspend fun signIn(email: String, password: String): Result<FirebaseUser>{
        return try{
            val authResult = repository.signIn(email, password).await()
            Result.success(authResult.user!!)
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }
}