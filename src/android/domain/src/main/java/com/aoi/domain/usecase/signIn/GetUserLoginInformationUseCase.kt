package com.aoi.domain.usecase.signIn

import com.aoi.data.repository.storedUserInformation.StoredUserInformationRepository
import javax.inject.Inject

/**
 * GetUserLoginInformationUseCase
 *
 * ログイン画面にてユーザーのログイン情報を取得するユースケース
 */
class GetUserLoginInformationUseCase @Inject constructor(
    private val storedUserInformationRepository: StoredUserInformationRepository
){

    /**
     * ユーザーのログイン情報を取得する
     *
     * @return ユーザーのログイン情報
     */
    fun getUserLoginInformation() : Pair<String, String>{
        return storedUserInformationRepository.getUserLoginInformation()
    }
}