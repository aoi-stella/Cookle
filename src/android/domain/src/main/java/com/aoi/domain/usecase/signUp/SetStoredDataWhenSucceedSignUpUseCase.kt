package com.aoi.domain.usecase.signUp

import com.aoi.data.repository.storedUserInformation.StoredUserInformationRepository
import javax.inject.Inject

/**
 * SetStoredDataWhenSucceedSignUpUseCase
 *
 * アカウント登録時の処理を記述するユースケース
 */
class SetStoredDataWhenSucceedSignUpUseCase @Inject constructor(
    private val storedUserInformationRepository: StoredUserInformationRepository
) {

    /**
     * setStoredDataWhenSucceedLoginUseCase
     *
     * アカウント作成成功時の処理を行う
     *
     * @param isStoreUserLoginDataInLocal ユーザーのログイン情報を端末に保存するかどうか
     * @param email ユーザーのメールアドレス
     * @param password ユーザーのパスワード
     * @return アカウント作成画面に遷移するかどうか
     */
    fun setStoredDataWhenSucceedSignUpUseCase(isStoreUserLoginDataInLocal: Boolean, email: String, password: String){
        if(isStoreUserLoginDataInLocal){
            storedUserInformationRepository.setUserLoginInformation(email, password)
        }
        storedUserInformationRepository.setAlreadyUserSignedUp(true)
    }
}