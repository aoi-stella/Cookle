package com.aoi.data.repository.storedUserInformation

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * StoredUserInformationRepository
 *
 * ユーザーが保存した情報に関するリポジトリ
 */
class StoredUserInformationRepository @Inject constructor(
    private val storedUserInfoSharedPreferences: SharedPreferences
) {

    /**
     * getAlreadyUserSignedUp
     *
     * ユーザーがアカウント作成済みかどうかを判断する
     *
     * @return ユーザーがアカウント作成済みかどうか
     */
    fun getAlreadyUserSignedUp(): Boolean {
        return storedUserInfoSharedPreferences.getBoolean("already_user_signed_up", false)
    }

    /**
     * setAlreadyUserSignedUp
     *
     * ユーザーがアカウント作成済みかどうかを設定する
     *
     * @param value ユーザーがアカウント作成済みかどうか
     */
    fun setAlreadyUserSignedUp(value: Boolean){
        storedUserInfoSharedPreferences.edit().putBoolean("already_user_signed_up", value).apply()
    }

    /**
     * setUserLoginInformation
     *
     * ユーザーのログイン情報を設定する
     *
     * @param email メールアドレス
     * @param password パスワード
     */
    fun setUserLoginInformation(email: String, password: String){
        storedUserInfoSharedPreferences.edit().putString("login_email", email).apply()
        storedUserInfoSharedPreferences.edit().putString("login_password", password).apply()
    }

    /**
     * getUserLoginInformation
     *
     * ユーザーのログイン情報を取得する
     *
     * @return ユーザーのログイン情報
     */
    fun getUserLoginInformation(): Pair<String, String> {
        val email = storedUserInfoSharedPreferences.getString("login_email", "")!!
        val password = storedUserInfoSharedPreferences.getString("login_password", "")!!
        return Pair(email, password)
    }
}