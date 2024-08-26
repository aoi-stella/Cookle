package com.aoi.cookle

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Cookle
 *
 * Applicationクラスです。
 */@HiltAndroidApp
class Cookle : Application() {
    /**
     * onCreate
     *
     * Applicationが生成された際に呼び出されます。
     */
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ModuleProvider {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideStoredUserInfoSharedPreferences(@ApplicationContext ctx: Context): SharedPreferences {
        return ctx.getSharedPreferences("stored_user_info", Context.MODE_PRIVATE)
    }
}
