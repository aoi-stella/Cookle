package com.aoi.presentation.navigator.application

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.home.HomeUI
import com.aoi.presentation.sign_in.SignInScreen
import com.aoi.presentation.sign_up.SignUpScreen
import com.aoi.presentation.splash.SplashScreen

/**
 * アプリケーションのエントリーポイント
 */
@Composable
fun Entry(){
    val navController = rememberNavController()
    ApplicationParentNavHost(navController = navController, startDestination = "splash")
}

/**
 * アプリケーション全体のナビゲーションを定義
 */
@Composable
fun ApplicationParentNavHost(
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
        composable(ApplicationParentNavHostDestination.SPLASH) {
            SplashScreen(onNavigate = { destination ->
                navController.navigate(destination) {
                    popUpTo(ApplicationParentNavHostDestination.SPLASH) { inclusive = true }
                }
            })
        }
        composable(
            ApplicationParentNavHostDestination.SIGN_IN,
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            SignInScreen(onNavigate = {
                navController.navigate(ApplicationParentNavHostDestination.HOME) {
                    popUpTo(ApplicationParentNavHostDestination.SIGN_IN) { inclusive = true }
                }
            })
        }
        composable(
            ApplicationParentNavHostDestination.SIGN_UP,
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            SignUpScreen(onNavigate = {
                navController.navigate(ApplicationParentNavHostDestination.HOME) {
                    popUpTo(ApplicationParentNavHostDestination.SIGN_UP) { inclusive = true }
                }
            })
        }
        composable(
            ApplicationParentNavHostDestination.HOME,
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            HomeUI()
        }
    }
}