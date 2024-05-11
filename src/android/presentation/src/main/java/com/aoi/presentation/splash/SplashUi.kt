package com.aoi.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aoi.presentation.R

/**
 * スプラッシュ画面のUI
 *
 * @param onNavigate: スプラッシュ画面から画面遷移を行う関数
 */
@Composable
fun SplashScreen(onNavigate: () -> Unit) {
    val splashFiles = R.raw.lottie_splash
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(splashFiles))
    val progress by animateLottieCompositionAsState(composition)

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    // 1回再生が終了したら呼び出す
    if(progress == 1f){
        onNavigate()
    }
}