package com.aoi.presentation.home.ingredient_addition

/**
 * コンテンツカード
 *
 * @param title タイトル
 * @param iconURL アイコンURL
 * @param category カテゴリー
 * @param uuid UUID
 */
data class ContentCard(
    val title: String,
    val iconURL: String,
    val category: String,
    val uuid: String,
)