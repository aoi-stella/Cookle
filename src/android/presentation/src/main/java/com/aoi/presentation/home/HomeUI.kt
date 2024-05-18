package com.aoi.presentation.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.aoi.presentation.navhost.MainMenuNavHost
import com.aoi.utility.ui.bottom_navigator.BottomNavigationItem
import com.aoi.utility.ui.bottom_navigator.CookleBottomNavigationBar

/**
 * ホーム画面の内容
 */
@Composable
fun HomeUI(vm: HomeViewModel = viewModel()) {
    HomeSetup()
}

/**
 * ホーム画面のセットアップ
 */
@Composable
fun HomeSetup() {
    val navigationItems = getNavigationItemList()
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

/**
 * ボトムナビゲーションのリストを取得
 */
@Composable
private fun getNavigationItemList(): List<BottomNavigationItem>{
    val items =
        remember{
            listOf(
                BottomNavigationItem(
                    route = "add_ingredient",
                    screenName = "食材追加",
                    selectedIcon = Icons.Filled.Home,
                    unselectedIcon = Icons.Outlined.Home,
                    hasNews = false,
                ),
                BottomNavigationItem(
                    route = "ingredient_list",
                    screenName = "食材一覧",
                    selectedIcon = Icons.Filled.Email,
                    unselectedIcon = Icons.Outlined.Email,
                    hasNews = false
                ),
                BottomNavigationItem(
                    route = "settings",
                    screenName = "設定",
                    selectedIcon = Icons.Filled.Settings,
                    unselectedIcon = Icons.Outlined.Settings,
                    hasNews = true,
                ),
            )
        }
    return items
}