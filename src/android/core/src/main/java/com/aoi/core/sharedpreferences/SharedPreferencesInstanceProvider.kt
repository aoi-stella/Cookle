package com.aoi.core.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferencesInstanceProvider
 *
 * SharedPreferencesのインスタンスを提供する。
 */
object SharedPreferencesInstanceProvider {
    // ユーザーが保存した情報に関するSharedPreferences
    lateinit var sharedPreferencesForStoredUserInfo: SharedPreferences

    /**
     * initializeAllSharedPreferences
     *
     * 全てのSharedPreferencesを初期化する。
     *
     * @param ctx Context
     */
    fun initializeAllSharedPreferences(ctx: Context) {
        sharedPreferencesForStoredUserInfo = ctx.getSharedPreferences("stored_user_info", Context.MODE_PRIVATE)
    }
}