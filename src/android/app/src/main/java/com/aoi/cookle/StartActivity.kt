package com.aoi.cookle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aoi.presentation.navigator.application.Entry
import com.aoi.presentation.theme.CookleTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * EntryActivity
 *
 * エントリーポイントとなるActivityです。
 */
@AndroidEntryPoint
class StartActivity : ComponentActivity() {
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