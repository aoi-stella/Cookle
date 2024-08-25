package com.aoi.domain.usecase.getStoredUserInformation

import com.aoi.data.repository.storedUserInformation.StoredUserInformationRepository

/**
 * CheckShouldNavigateToSignUpScreenUseCase
 *
 * アカウント作成画面に遷移するかどうかを判断するユースケース
 */
class GetUserLoginInformationUseCase {
    private val storedUserInformationRepository = StoredUserInformationRepository()

    /**
     * ユーザーのログイン情報を取得する
     *
     * @return ユーザーのログイン情報
     */
    fun getUserLoginInformation() : Pair<String, String>{
        return storedUserInformationRepository.getUserLoginInformation()
    }
}