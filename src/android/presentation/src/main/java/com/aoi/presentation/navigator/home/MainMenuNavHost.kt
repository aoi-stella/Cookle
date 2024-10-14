package com.aoi.presentation.navigator.home

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
import com.aoi.presentation.navigator.ingredient_view.IngredientAdditionNavHost

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
            "ingredient_view",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            IngredientAdditionNavHost(
                navController = rememberNavController(),
                startDestination = "ingredient_view",
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            "ingredient_addition",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
        }
        composable(
            "app_settings",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
        }
    }
}