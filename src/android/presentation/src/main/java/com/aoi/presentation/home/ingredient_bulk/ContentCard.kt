package com.aoi.presentation.home.ingredient_bulk

/**
 * コンテンツカード
 *
 * @param title タイトル
 * @param iconURL アイコンURL
 * @param category カテゴリー
 */
data class ContentCard(
    val title: String,
    val iconURL: String,
    val category: String,
)