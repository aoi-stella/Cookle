package com.aoi.presentation.home.ingredient_bulk

/**
 * 食材一括管理画面の状態
 *
 * @param selectedCategory 選択されたカテゴリ
 * @param categoryList カテゴリリスト
 * @param updateIngredient 食材カテゴリの更新処理
 * @param contentList コンテンツリスト
 */
data class BulkIngredientScreenState (
    val selectedCategory: String,
    val categoryList: List<String>,
    val updateIngredient: Boolean,
    val contentList: List<ContentCard>
)

/**
 * 食材一括管理画面のイベント
 *
 * @param onChangedCategory カテゴリが変更されたときの処理
 * @param updateIngredientCategory 食材カテゴリの更新処理
 * @param onClickContentCard コンテンツカードがクリックされたときの処理
 */
data class BulkIngredientScreenEvent (
    val onChangedCategory: (String) -> Unit,
    val updateIngredientCategory: () -> Unit,
    val onClickContentCard: () -> Unit,
)