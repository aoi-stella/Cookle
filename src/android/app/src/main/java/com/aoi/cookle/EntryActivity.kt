package com.aoi.cookle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aoi.presentation.navhost.Entry
import com.aoi.presentation.theme.CookleTheme

/**
 * EntryActivity
 *
 * エントリーポイントとなるActivityです。
 */
@SuppressLint("CustomSplashScreen")
class EntryActivity : ComponentActivity() {
    /**
     * onCreate
     *
     * Activityが生成された際に呼び出されます。
     * ここでComposeを使用して画面を構築します。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CookleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Entry()
                }
            }
        }
    }
}