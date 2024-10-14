package com.aoi.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.navigator.home.MainMenuNavHost
import com.aoi.utility.ui.bottom_navigator.CookleBottomNavigationBar

/**
 * ホーム画面の内容
 */
@Composable
fun HomeUI(vm: HomeViewModel = hiltViewModel()) {
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
            startDestination = "bulk_ingredient",
            modifier = Modifier.padding(paddingValues))
    }
}