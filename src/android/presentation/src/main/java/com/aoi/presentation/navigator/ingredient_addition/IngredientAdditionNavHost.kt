package com.aoi.presentation.navigator.ingredient_view

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aoi.presentation.home.ingredient_addition.IngredientAddition
import com.aoi.presentation.home.ingredient_detail.IngredientDetailUI

/**
 * 食材リスト画面のナビゲーションを定義
 *
 * @param navController ナビゲーションコントローラー
 * @param startDestination 開始地点
 * @param modifier Modifier
 */
@Composable
fun IngredientAdditionNavHost(
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

    var currentRoute = remember { "ingredient_view" }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(
            "ingredient_view",
            enterTransition = { if(currentRoute == "ingredient_view") slideInScreenEnterTransition else slideOutScreenEnterTransition },
            exitTransition = { slideInScreenExitTransition }
        ) {
            currentRoute = "ingredient_view"
            IngredientAddition(
                onNavigateForAddIngredient = { navController.navigate("ingredient_view") },
                onNavigateForIngredientDetail = { selectedIngredientId ->
                    navController.navigate("ingredient_detail/$selectedIngredientId") {
                        popUpTo("ingredient_view") { inclusive = false }
                    }
                }
            )
        }
        composable(
            "ingredient_detail/{ingredientId}",
            arguments = listOf(navArgument("ingredientId") { type = NavType.StringType }),
            enterTransition = { slideInScreenEnterTransition },
            exitTransition = { slideOutScreenExitTransition }
        ) { backStackEntry ->
            currentRoute = "ingredient_detail"
            val ingredientId = backStackEntry.arguments?.getString("ingredientId")
            IngredientDetailUI(ingredientId = ingredientId!!)
        }
    }
}