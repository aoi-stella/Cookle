package com.aoi.presentation.home.ingredient_bulk

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 食材一括管理画面のViewModel
 */
class BulkIngredientViewModel: ViewModel() {

    // 選択されたカテゴリ
    private val _selectedCategory = MutableStateFlow("全て")
    val selectedCategory = _selectedCategory.asStateFlow()

    // カテゴリリスト
    private val _categoryList = MutableStateFlow(listOf("全て", "野菜", "果物", "肉", "魚", "乳製品", "穀物", "その他"))
    val categoryList = _categoryList.asStateFlow()

    /**
     * カテゴリが変更されたときの処理
     *
     * @param category カテゴリ
     */
    fun onChangedCategory(category: String) {
        _selectedCategory.value = category
    }
}