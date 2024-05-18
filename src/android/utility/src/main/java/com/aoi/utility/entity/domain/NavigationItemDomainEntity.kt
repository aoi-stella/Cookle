package com.aoi.utility.entity.domain

/**
 * ナビゲーションアイテムのドメインエンティティ
 *
 * @param route ルート
 * @param screenName 画面名
 * @param hasNews お知らせがあるか
 */
data class NavigationItemDomainEntity(
    val route: String,
    val screenName: String,
    val hasNews: Boolean
)