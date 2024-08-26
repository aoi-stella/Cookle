package com.aoi.domain.usecase.signIn

import com.aoi.data.repository.authentication.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * SignInUseCase
 *
 * サインイン画面のユースケース
 */
class SignInUseCase @Inject constructor(
    private val repository: AuthenticationRepository
){
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