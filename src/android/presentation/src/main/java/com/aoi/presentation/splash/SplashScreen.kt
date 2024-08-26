package com.aoi.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.aoi.presentation.R

/**
 * スプラッシュ画面のUI
 *
 * @param onNavigate: スプラッシュ画面から画面遷移を行う関数
 */
@Composable
fun SplashScreen(onNavigate: (String) -> Unit, vm: SplashViewModel = hiltViewModel()) {
    val navigateDestination by vm.nextDestination.collectAsState()
    val splashFiles = R.raw.lottie_splash
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(splashFiles))

    LaunchedEffect(navigateDestination){
        onNavigate(navigateDestination)
    }

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}