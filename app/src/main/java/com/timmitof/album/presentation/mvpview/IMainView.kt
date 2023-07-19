package com.timmitof.album.presentation.mvpview

import com.timmitof.album.database.entity.Image
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface IMainView : MvpView {
    fun setImages(list: List<Image>?)

    fun showToast(message: String?)
}