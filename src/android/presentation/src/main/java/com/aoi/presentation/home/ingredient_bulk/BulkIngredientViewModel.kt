package com.aoi.presentation.home.ingredient_bulk

import androidx.lifecycle.ViewModel
import com.aoi.domain.usecase.ingredient_category.GetIngredientCategoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 食材一括管理画面のViewModel
 */
class BulkIngredientViewModel(
    private val getIngredientCategoryUseCase: GetIngredientCategoryUseCase = GetIngredientCategoryUseCase()
): ViewModel() {

    // 選択されたカテゴリ
    private val _selectedCategory = MutableStateFlow("全て")
    val selectedCategory = _selectedCategory.asStateFlow()

    // カテゴリリスト
    private val _categoryList = MutableStateFlow(listOf(""))
    val categoryList = _categoryList.asStateFlow()

    private val _updateIngredientCategory = MutableStateFlow(true)
    val updateIngredientCategory = _updateIngredientCategory.asStateFlow()

    /**
     * カテゴリが変更されたときの処理
     *
     * @param category カテゴリ
     */
    fun onChangedCategory(category: String) {
        _selectedCategory.value = category
    }

    /**
     * 食材カテゴリの更新処理
     */
    fun updateIngredientCategory() {
        _categoryList.value = getIngredientCategoryUseCase.getIngredientCategoryList()
        _updateIngredientCategory.value = false
    }
}