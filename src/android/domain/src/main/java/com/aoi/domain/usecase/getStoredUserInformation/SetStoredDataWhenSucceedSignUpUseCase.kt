package com.aoi.domain.usecase.getStoredUserInformation

import com.aoi.data.repository.storedUserInformation.StoredUserInformationRepository

/**
 * CheckShouldNavigateToSignUpScreenUseCase
 *
 * アカウント作成画面に遷移するかどうかを判断するユースケース
 */
class SetStoredDataWhenSucceedSignUpUseCase {
    private val storedUserInformationRepository = StoredUserInformationRepository()

    /**
     * setStoredDataWhenSucceedLoginUseCase
     *
     * アカウント作成成功時の処理を行う
     *
     * @return アカウント作成画面に遷移するかどうか
     */
    fun setStoredDataWhenSucceedSignUpUseCase(){
        //TODO: おそらく他にも追加するロジックあるのでまとめること
        storedUserInformationRepository.setAlreadyUserSignedUp(true)
    }
}