package com.aoi.presentation.home.ingredient_addition

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
class IngredientAdditionViewModel @Inject constructor(
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

    // コンテンツリスト
    private val _contentList = MutableStateFlow(listOf<ContentCard>())
    val contentList = _contentList.asStateFlow()

    // 選択されたカードのid
    private val _selectedCardId = MutableStateFlow("")
    val selectedCardId = _selectedCardId.asStateFlow()

    init {
        _contentList.value = listOf(
            ContentCard("トマト", "", "野菜", "1"),
            ContentCard("じゃがいも", "", "野菜", "2"),
            ContentCard("人参", "", "野菜", "3"),
            ContentCard("パセリ", "", "野菜", "4"),
            ContentCard("ハンバーグ用牛肉", "", "肉", "5"),
            ContentCard("挽肉", "", "肉", "6"),
            ContentCard("ステーキ用フィレステーキ", "", "肉", "7"),
            ContentCard("牛乳", "", "乳製品", "8"),
            ContentCard("チェダーチーズ", "", "乳製品", "9"),
            ContentCard("カマンベールチーズ", "", "乳製品", "10"),
            ContentCard("ラクレット", "", "乳製品", "11"),
            ContentCard("鯵", "", "魚", "12"),
            ContentCard("秋刀魚", "", "魚", "13"),
            ContentCard("鮭", "", "魚", "14"),
            ContentCard("浅利", "", "魚", "15"),
        )
    }

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