package com.aoi.presentation.home.ingredient_bulk

import androidx.lifecycle.ViewModel
import com.aoi.domain.usecase.ingredient_category.GetIngredientCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * 食材一括管理画面のViewModel
 */
@HiltViewModel
class BulkIngredientViewModel @Inject constructor(
    private val getIngredientCategoryUseCase: GetIngredientCategoryUseCase
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