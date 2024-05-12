package com.aoi.core.firebase

import com.google.firebase.auth.FirebaseAuth

/**
 * FirebaseAuthManager
 *
 * Firebase Authenticationの管理を行うクラスです。
 * インスタンスの作成及び保持を行う為だけなので、自体を用いた操作は行いません。
 */
object FirebaseAuthManager {
    var auth: FirebaseAuth? = null
        private set

    /**
     * initialize
     *
     * FirebaseAuthManagerを初期化します。
     */
    fun initialize() {
        auth = FirebaseAuth.getInstance()
    }
}