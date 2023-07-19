package com.timmitof.album.presentation.mvpview

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ICameraView : MvpView {
    fun showToast(message: String?)
}