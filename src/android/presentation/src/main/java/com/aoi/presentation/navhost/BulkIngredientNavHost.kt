package com.aoi.presentation.navhost

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
    val slideInScreenEnterTransition = slideInHorizontally(
        initialOffsetX = { 1100 },
        animationSpec = tween(800)
    )

    val slideOutScreenExitTransition = slideOutHorizontally(
        targetOffsetX = { 1100 },
        animationSpec = tween(800)
    )

    val slideOutScreenEnterTransition = slideInHorizontally(
        initialOffsetX = { -1100 },
        animationSpec = tween(800)
    )

    val slideInScreenExitTransition = slideOutHorizontally(
        targetOffsetX = { -1100 },
        animationSpec = tween(800)
    )

    var currentRoute = remember { "bulk_ingredient" }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            "bulk_ingredient",
            enterTransition = { if(currentRoute == "bulk_ingredient") slideInScreenEnterTransition else slideOutScreenEnterTransition },
            exitTransition = { slideInScreenExitTransition }
        ) {
            currentRoute = "bulk_ingredient"
            BulkIngredientUI(
                onNavigateForAddIngredient = { navController.navigate("bulk_ingredient") },
                onNavigateForIngredientDetail = { navController.navigate("ingredient_detail"){
                    popUpTo("bulk_ingredient") { inclusive = false }
                } }
            )
        }
        composable(
            "ingredient_detail",
            enterTransition = { slideInScreenEnterTransition },
            exitTransition = { slideOutScreenExitTransition }
        ) {
            currentRoute = "ingredient_detail"
            IngredientDetailUI()
        }
    }
}