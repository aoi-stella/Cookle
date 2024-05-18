package com.aoi.presentation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

/**
 * ホーム画面のナビゲーションを定義
 */
@Composable
fun MainMenuNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier
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
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            "add_ingredient",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            navController.navigate("add_ingredient") {
                popUpTo("add_ingredient") { inclusive = true }
            }
        }
        composable(
            "ingredient_list",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            navController.navigate("ingredient_list") {
                popUpTo("ingredient_list") { inclusive = true }
            }
        }
        composable(
            "settings",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            navController.navigate("settings") {
                popUpTo("settings") { inclusive = true }
            }
        }
    }
}