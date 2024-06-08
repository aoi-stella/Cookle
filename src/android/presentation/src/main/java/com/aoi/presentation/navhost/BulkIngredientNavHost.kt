package com.aoi.presentation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aoi.presentation.home.ingredient_bulk.BulkIngredientUI
import com.aoi.presentation.home.ingredient_detail.IngredientDetailUI

/**
 * 食材リスト画面のナビゲーションを定義
 *
 * @param navController ナビゲーションコントローラー
 * @param startDestination 開始地点
 * @param modifier Modifier
 */
@Composable
fun BulkIngredientNavHost(
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
            BulkIngredientUI(
                onNavigateForAddIngredient = { navController.navigate("bulk_ingredient") },
                onNavigateForIngredientDetail = { navController.navigate("ingredient_detail"){
                    popUpTo("bulk_ingredient") { inclusive = false }
                } }
            )
        }
        composable(
            "ingredient_detail",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            IngredientDetailUI()
        }
        composable(
            "ingredient_detail",
            enterTransition = { commonEnterTransition },
            exitTransition = { commonExitTransition }
        ) {
            // IngredientDetailUIをここに記述
        }
    }
}