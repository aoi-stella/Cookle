package com.aoi.presentation.splash

import androidx.lifecycle.ViewModel
import com.aoi.domain.usecase.appLaunch.CheckShouldNavigateToSignUpScreenUseCase
import com.aoi.presentation.navigator.application.ApplicationParentNavHostDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * SplashViewModel
 *
 * スプラッシュ画面のViewModel。
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkShouldNavigateToSignUpScreenUseCase: CheckShouldNavigateToSignUpScreenUseCase
): ViewModel(){
    // スプラッシュ画面の次の遷移先
    private val _nextDestination = MutableStateFlow(ApplicationParentNavHostDestination.SIGN_IN)
    val nextDestination = _nextDestination.asStateFlow()

    init {
        initNextDestination()
    }

    /**
     * setNextDestination
     *
     * 次の遷移先を設定する。
     */
    private fun initNextDestination(){
        if(checkShouldNavigateToSignUpScreenUseCase.getShouldNavigateToSignUpScreen()){
            _nextDestination.value = ApplicationParentNavHostDestination.SIGN_UP
        }
        else{
            _nextDestination.value = ApplicationParentNavHostDestination.SIGN_IN
        }
    }
}