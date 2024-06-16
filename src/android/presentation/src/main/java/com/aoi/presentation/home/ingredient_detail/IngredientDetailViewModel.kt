package com.aoi.presentation.home.ingredient_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 食材詳細画面のViewModel
 */
class IngredientDetailViewModel: ViewModel() {
    // 通知設定の有効無効
    private val _enabledNotify = MutableStateFlow(false)
    val enabledNotify = _enabledNotify.asStateFlow()

    // 通知日時
    private val _notifyDateTime = MutableStateFlow("")
    val notifyDateTime = _notifyDateTime.asStateFlow()

    // 食材名
    private val _ingredientName = MutableStateFlow("")
    val ingredientName = _ingredientName.asStateFlow()

    // 賞味期限
    private val _expirationDate = MutableStateFlow("")
    val expirationDate = _expirationDate.asStateFlow()

    /**
     * onChangedEnabledNotify
     *
     * @param enabled 通知設定の有効無効
     */
    fun onChangedEnabledNotify(enabled: Boolean) {
        _enabledNotify.value = enabled
    }
}