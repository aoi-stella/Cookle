package com.aoi.domain.usecase.signUp

import com.aoi.data.repository.authentication.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

/**
 * SignUpUseCase
 *
 * サインアップのユースケース
 */
class SignUpUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {

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