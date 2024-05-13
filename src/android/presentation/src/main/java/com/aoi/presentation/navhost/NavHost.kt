package com.aoi.presentation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.authentication.sign_in.SignInScreen
import com.aoi.presentation.splash.SplashScreen

/**
 * アプリケーションのエントリーポイント
 */
@Composable
fun Entry(){
    val navController = rememberNavController()
    AppNavHost(navController = navController, startDestination = "splash")
}

/**
 * アプリケーションのナビゲーションを定義
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String
) {
    val commonEnterTransition = slideInHorizontally(
        initialOffsetX = { 1100 },
        animationSpec = tween(800)
    )

    val commonExitTransition = slideOutHorizontally(
        targetOffsetX = { -1100 },
        animationSpec = tween(800)
    )

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("splash") {
            SplashScreen(onNavigate = {
                navController.navigate("signin") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable(
            "signin",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            SignInScreen(onNavigate = {
                navController.navigate("home")
            })
        }
    }
}