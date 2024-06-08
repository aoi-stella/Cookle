package com.aoi.data.repository.ingredient_category

/**
 * 食材カテゴリリスト操作用リポジトリ
 */
class IngredientCategoryRepository {
    /**
     * 食材カテゴリリストを取得する
     *
     * @return 食材カテゴリリスト
     */
    fun getIngredientCategoryList(): List<String> {
        return listOf("全て", "野菜", "果物", "肉", "魚", "乳製品", "穀物", "その他")
    }
}