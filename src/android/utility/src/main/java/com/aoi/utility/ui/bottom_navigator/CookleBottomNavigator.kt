package com.aoi.utility.ui.bottom_navigator

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

/**
 * ボトムナビゲーションバー
 *
 * @param navController ナビゲーションコントローラ
 * @param items ナビゲーションアイテムのリスト
 */
@Composable
fun CookleBottomNavigationBar(
    navController: NavController,
    items: List<BottomNavigationItem>) {
    val navBackStackEntry by navController.currentBackStackEntryFlow.collectAsState(initial = null)
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                label = {
                    Text(text = item.screenName)
                },
                alwaysShowLabel = true,
                icon = {
                    BadgedBox(
                        badge = {
                            if(item.hasNews) {
                                Badge()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (currentRoute == item.route) {
                                item.selectedIcon
                            } else item.unselectedIcon,
                            contentDescription = item.screenName
                        )
                    }
                }
            )
        }
    }
}