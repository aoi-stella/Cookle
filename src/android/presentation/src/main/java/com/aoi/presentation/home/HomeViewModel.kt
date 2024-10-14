package com.aoi.presentation.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.aoi.domain.usecase.navigation.GetHomeNavigationItemsUseCase
import com.aoi.utility.entity.ui.NavigationItemUIEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ホーム画面のViewModel
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeNavigationItemsUseCase: GetHomeNavigationItemsUseCase
): ViewModel() {
    /**
     * ナビゲーションアイテムを取得する
     */
    fun getNavigationItems(): List<NavigationItemUIEntity>{
        val itemsListDomainType = getHomeNavigationItemsUseCase.getNavigationItems()
        val itemsListUIType: List<NavigationItemUIEntity> = itemsListDomainType.map {
            //TODO: アイコン取得のロジック後でいい感じにしなきゃね
            val selectedIcon: ImageVector = when(it.route){
                "bulk_ingredient" -> Icons.Default.Home
                "ingredient_list" -> Icons.Default.Email
                "settings" -> Icons.Default.Settings
                else -> Icons.Default.Home
            }

            val unselectedIcon: ImageVector = when(it.route){
                "bulk_ingredient" -> Icons.Default.Home
                "ingredient_list" -> Icons.Default.Email
                "settings" -> Icons.Default.Settings
                else -> Icons.Default.Home
            }

            NavigationItemUIEntity(
                route = it.route,
                screenName = it.screenName,
                selectedIcon = selectedIcon,
                unselectedIcon = unselectedIcon,
                hasNews = it.hasNews
            )
        }
        return itemsListUIType
    }
}