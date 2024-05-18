package com.aoi.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.navhost.MainMenuNavHost
import com.aoi.utility.ui.bottom_navigator.CookleBottomNavigationBar

/**
 * ホーム画面の内容
 */
@Composable
fun HomeUI(vm: HomeViewModel = viewModel()) {
    HomeSetup(vm)
}

/**
 * ホーム画面のセットアップ
 */
@Composable
fun HomeSetup(vm: HomeViewModel) {
    val navigationItems = vm.getNavigationItems()
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            CookleBottomNavigationBar(
                navController = bottomNavController,
                items = navigationItems
            )
        }
    ) { paddingValues ->
        MainMenuNavHost(
            navController = bottomNavController,
            startDestination = "add_ingredient",
            modifier = Modifier.padding(paddingValues))
    }
}