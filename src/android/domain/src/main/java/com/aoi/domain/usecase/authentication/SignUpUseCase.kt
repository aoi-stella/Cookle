package com.aoi.domain.usecase.authentication

import com.aoi.data.repository.authentication.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

/**
 * SignUpUseCase
 *
 * サインアップのユースケース
 */
class SignUpUseCase {
    private val repository: AuthenticationRepository = AuthenticationRepository()

    /**
     * サインアップ
     *
     * @param email メールアドレス
     * @param password パスワード
     */
    suspend fun signUp(email: String, password: String): Result<FirebaseUser>{
        return try{
            val authResult = repository.signUp(email, password).await()
            Result.success(authResult.user!!)
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }
}