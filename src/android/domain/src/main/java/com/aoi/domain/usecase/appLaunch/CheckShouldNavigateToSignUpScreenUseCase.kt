package com.aoi.domain.usecase.appLaunch

import com.aoi.data.repository.storedUserInformation.StoredUserInformationRepository

/**
 * CheckShouldNavigateToSignUpScreenUseCase
 *
 * アカウント作成画面に遷移するかどうかを判断するユースケース
 */
class CheckShouldNavigateToSignUpScreenUseCase {
    private val storedUserInformationRepository = StoredUserInformationRepository()

    /**
     * getShouldNavigateToSignUpScreen
     *
     * アカウント作成画面に遷移するかどうかを取得する。
     *
     * @return アカウント作成画面に遷移するかどうか
     */
    fun getShouldNavigateToSignUpScreen(): Boolean {
        return !storedUserInformationRepository.getAlreadyUserSignedUp()
    }
}