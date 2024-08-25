package com.aoi.cookle

import android.app.Application
import android.content.Context
import com.aoi.core.firebase.FirebaseAuthManager
import com.aoi.core.sharedpreferences.SharedPreferencesInstanceProvider
import com.google.firebase.FirebaseApp

/**
 * Cookle
 *
 * Applicationクラスです。
 */
class Cookle : Application() {
    /**
     * onCreate
     *
     * Applicationが生成された際に呼び出されます。
     */
    override fun onCreate() {
        super.onCreate()
        initFirebase()
        initSharedPreferences(this)
    }

    /**
     * initFirebase
     *
     * Firebaseを初期化します。
     */
    private fun initFirebase(){
        FirebaseApp.initializeApp(this)
        FirebaseAuthManager.initialize()
    }

    /**
     * initSharedPreferences
     *
     * SharedPreferencesを初期化します。
     * @param ctx Context
     */
    private fun initSharedPreferences(ctx: Context){
        SharedPreferencesInstanceProvider.initializeAllSharedPreferences(ctx)
    }
}