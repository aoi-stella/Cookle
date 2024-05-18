package com.aoi.domain.usecase.navigation

import com.aoi.data.repository.navigation_items.NavigationItemsRepository
import com.aoi.utility.entity.domain.NavigationItemDomainEntity

/**
 * ホーム画面のナビゲーションアイテムを取得するユースケース
 */
class GetHomeNavigationItemsUseCase {
    private val getNavigationItemsRepository = NavigationItemsRepository()

    /**
     * ナビゲーションアイテムを取得する
     *
     * @return ナビゲーションアイテムリスト
     */
    fun getNavigationItems(): List<NavigationItemDomainEntity>{
        return getNavigationItemsRepository.getNavigationItems()
    }
}