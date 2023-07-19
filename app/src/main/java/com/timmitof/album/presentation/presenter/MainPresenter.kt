package com.timmitof.album.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.timmitof.album.domain.repository.GalleryRepository
import com.timmitof.album.presentation.mvpview.IMainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val galleryRepository: GalleryRepository?) : MvpPresenter<IMainView>() {

    fun getAllImages() {
        Log.e("GetImage", "GET IMAGE")
        val listImages = galleryRepository?.getAllImages()
        viewState.setImages(listImages)
    }
}