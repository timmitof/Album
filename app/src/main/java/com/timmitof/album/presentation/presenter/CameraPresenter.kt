package com.timmitof.album.presentation.presenter

import android.database.Observable
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.timmitof.album.database.entity.Coordinate
import com.timmitof.album.database.entity.Image
import com.timmitof.album.domain.repository.GalleryRepository
import com.timmitof.album.presentation.mvpview.ICameraView
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.java.KoinJavaComponent.inject
import java.util.Date

@InjectViewState
class CameraPresenter : MvpPresenter<ICameraView>() {

    private val galleryRepository: GalleryRepository by inject(GalleryRepository::class.java)

    fun savePhoto(image: Bitmap?) {
        val image = Image(
            image = image,
            date = Date(),
            coordinate = Coordinate(longitude = 0f, latitude = 0f),
            id = null
        )

        galleryRepository.addImage(image)
    }
}