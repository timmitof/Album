package com.timmitof.album.presentation.mvpview

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.timmitof.album.database.entity.Image

@StateStrategyType(AddToEndSingleStrategy::class)

interface IMainView : MvpView {
    fun setImages(list: List<Image>?)
}