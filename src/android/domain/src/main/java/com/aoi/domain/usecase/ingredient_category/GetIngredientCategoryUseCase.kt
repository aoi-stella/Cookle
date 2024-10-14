package com.aoi.domain.usecase.ingredient_category

import com.aoi.data.repository.ingredient_category.IngredientCategoryRepository
import javax.inject.Inject

/**
 * 食材カテゴリを取得するユースケース
 */
class GetIngredientCategoryUseCase @Inject constructor(
    private val ingredientCategoryRepository: IngredientCategoryRepository
) {
    /**
     * 食材カテゴリリストを取得
     *
     * @return 食材カテゴリリスト
     */
    fun getIngredientCategoryList(): List<String> {
        return ingredientCategoryRepository.getIngredientCategoryList()
    }
}