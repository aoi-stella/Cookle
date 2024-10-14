package com.aoi.data.repository.navigation_items

import com.aoi.utility.entity.domain.NavigationItemDomainEntity
import javax.inject.Inject

/**
 * ナビゲーションアイテムのリポジトリ
 */
class NavigationItemsRepository @Inject constructor() {
    /**
     * ナビゲーションアイテムを取得する
     *
     * @return ナビゲーションアイテムリスト
     */
    fun getNavigationItems(): List<NavigationItemDomainEntity> {
        return listOf(
            NavigationItemDomainEntity(
                route = "bulk_ingredient",
                screenName = "食材追加",
                hasNews = false,
            ),
            NavigationItemDomainEntity(
                route = "ingredient_list",
                screenName = "食材一覧",
                hasNews = false
            ),
            NavigationItemDomainEntity(
                route = "settings",
                screenName = "設定",
                hasNews = true,
            ),
        )
    }
}