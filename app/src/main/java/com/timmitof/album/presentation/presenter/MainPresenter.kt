package com.timmitof.album.presentation.presenter

import android.util.Log
import android.widget.Toast
import com.timmitof.album.database.entity.Image
import com.timmitof.album.domain.repository.GalleryRepository
import com.timmitof.album.presentation.mvpview.IMainView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import org.koin.java.KoinJavaComponent.inject

@InjectViewState
class MainPresenter : MvpPresenter<IMainView>() {

    private val galleryRepository: GalleryRepository by inject(GalleryRepository::class.java)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getAllImages()
    }

    fun getAllImages() {
        galleryRepository.getAllImages()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { images ->
                    viewState.setImages(images)
                },
                { error ->
                    viewState.showToast("Error: ${error.message}")
                }
            ).isDisposed
    }

    fun deleteImage(image: Image) {
        galleryRepository.deleteImage(image)
    }
}