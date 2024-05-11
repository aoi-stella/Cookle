package com.aoi.cookle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aoi.cookle.ui.theme.CookleTheme

/**
 * SplashActivity
 *
 * スプラッシュ画面を表示するActivityです。
 */
class SplashActivity : ComponentActivity() {
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

/**
 * LoadSplash
 *
 * スプラッシュ画面を表示します。
 * LottieAnimationを使用してアニメーションを表示します。
 * 無限再生する為、呼び元から停止する必要があります。
 */
@Composable
fun LoadSplash(){
    val splashFiles = R.raw.lottie_splash
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(splashFiles))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}

/**
 * Entry
 *
 * ここからアプリケーションが始まります。
 */
@Composable
fun Entry() {
    LoadSplash()
}

/**
 * EntryPreview
 *
 * Entryのプレビューを表示します。
 */
@Preview(showBackground = true)
@Composable
fun EntryPreview() {
    CookleTheme {
        Entry()
    }
}