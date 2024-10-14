package com.aoi.presentation.home.ingredient_detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * 食材詳細画面のViewModel
 */
@HiltViewModel
class IngredientDetailViewModel @Inject constructor(): ViewModel() {
    // 通知設定の有効無効
    private val _enabledNotify = MutableStateFlow(false)
    val enabledNotify = _enabledNotify.asStateFlow()

    // 通知日時
    private val _notifyDate = MutableStateFlow("")
    val notifyDate = _notifyDate.asStateFlow()

    // 食材名
    private val _ingredientName = MutableStateFlow("")
    val ingredientName = _ingredientName.asStateFlow()

    // 賞味期限
    private val _expirationDate = MutableStateFlow("")
    val expirationDate = _expirationDate.asStateFlow()

    // 通知月日設定ダイアログの表示及び非表示
    private val _showDateDialog = MutableStateFlow(false)
    val showDateDialog = _showDateDialog.asStateFlow()

    // 通知時刻設定ダイアログの表示及び非表示
    private val _showTimeDialog = MutableStateFlow(false)
    val showTimeDialog = _showTimeDialog.asStateFlow()

    private val _notifyTime = MutableStateFlow("")
    val notifyTime = _notifyTime.asStateFlow()

    /**
     * onChangedEnabledNotify
     *
     * @param enabled 通知設定の有効無効
     */
    fun onChangedEnabledNotify(enabled: Boolean) {
        _enabledNotify.value = enabled
    }

    /**
     * onChangeDialogVisibleState
     *
     * @param isShow 通知ダイアログのON/OFF
     */
    fun onChangeDateDialogVisibleState(isShow: Boolean){
        _showDateDialog.value = isShow
    }

    /**
     * onChangedNotifyDate
     *
     * @param year 年
     * @param month 月
     * @param day 日
     */
    fun onChangeNotifyDate(year: Int, month: Int, day: Int){
        _notifyDate.value = "$year/$month/$day"
        _showDateDialog.value = false
        _showTimeDialog.value = true
    }

    /**
     * onChangedNotifyDateTime
     *
     * @param hour 時
     * @param minute 分
     */
    fun onChangeNotifyTime(hour: Int, minute: Int){
        _notifyTime.value = "$hour:$minute"
        _showTimeDialog.value = false
        _enabledNotify.value = true
    }

    /**
     * onChangedIngredientName
     */
    fun resetDatePickerVisibleState(){
        _showDateDialog.value = false
    }

    /**
     * resetTimePickerVisibleState
     */
    fun resetTimePickerVisibleState(){
        _showTimeDialog.value = false
    }
}