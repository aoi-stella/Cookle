package com.aoi.data.model

/**
 * アプリ内のユーザーデータモデル
 */
object AuthenticationUserDataModel {
    data class AccountData(
        val email: String,
        val password: String,
    )

    // ログイン情報
    var accountData: AccountData? = null
        private set

    /**
     * アカウントデータを更新する
     * @param newAccountData 新しいアカウントデータ
     */
    fun updateAccountData(newAccountData: AccountData) {
        accountData = newAccountData
    }
}