package com.aoi.utility.entity.ui

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * ナビゲーションアイテムのUIエンティティ
 *
 * @param route ルート
 * @param screenName 画面名
 * @param selectedIcon 選択時のアイコン
 * @param unselectedIcon 非選択時のアイコン
 * @param hasNews お知らせがあるか
 */
class NavigationItemUIEntity(
    val route: String,
    val screenName: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean
)