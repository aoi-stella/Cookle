package com.aoi.cookle

import android.app.Application
import com.aoi.core.firebase.FirebaseAuthManager
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
}