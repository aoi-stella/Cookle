package com.aoi.presentation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
            "bulk_ingredient",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            BulkIngredientNavHost(
                navController = rememberNavController(),
                startDestination = "bulk_ingredient",
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            "ingredient_list",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
        }
        composable(
            "settings",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
        }
    }
}