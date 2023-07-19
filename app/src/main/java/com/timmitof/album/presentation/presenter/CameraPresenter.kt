package com.timmitof.album.presentation.presenter

import android.net.Uri
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.timmitof.album.database.entity.Coordinate
import com.timmitof.album.database.entity.Image
import com.timmitof.album.domain.repository.GalleryRepository
import com.timmitof.album.presentation.mvpview.ICameraView
import java.util.Date
import javax.inject.Inject

@InjectViewState
class CameraPresenter @Inject constructor(private val galleryRepository: GalleryRepository?) : MvpPresenter<ICameraView>() {

    fun savePhoto(url: Uri?) {
        val image = Image(
            url = url,
            date = Date(),
            coordinate = Coordinate(longitude = 0f, latitude = 0f),
            id = null
        )
        galleryRepository?.addImage(image)
    }
}