package com.aoi.data.repository.storedUserInformation

import com.aoi.core.sharedpreferences.SharedPreferencesInstanceProvider

/**
 * StoredUserInformationRepository
 *
 * ユーザーが保存した情報に関するリポジトリ
 */
class StoredUserInformationRepository {
    private val sharedPreferences =
        SharedPreferencesInstanceProvider.sharedPreferencesForStoredUserInfo

    /**
     * getAlreadyUserSignedUp
     *
     * ユーザーがアカウント作成済みかどうかを判断する
     *
     * @return ユーザーがアカウント作成済みかどうか
     */
    fun getAlreadyUserSignedUp(): Boolean {
        return sharedPreferences.getBoolean("already_user_signed_up", false)
    }

    /**
     * setAlreadyUserSignedUp
     *
     * ユーザーがアカウント作成済みかどうかを設定する
     *
     * @param value ユーザーがアカウント作成済みかどうか
     */
    fun setAlreadyUserSignedUp(value: Boolean){
        sharedPreferences.edit().putBoolean("already_user_signed_up", value).apply()
    }
}