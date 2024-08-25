package com.aoi.domain.usecase.getStoredUserInformation

import com.aoi.data.repository.storedUserInformation.StoredUserInformationRepository

/**
 * CheckShouldNavigateToSignUpScreenUseCase
 *
 * アカウント作成画面に遷移するかどうかを判断するユースケース
 */
class SetUserLoginInformationUseCase {
    private val storedUserInformationRepository = StoredUserInformationRepository()

    fun setUserLoginInformation(email: String, password: String){
        storedUserInformationRepository.setUserLoginInformation(email, password)
    }
}