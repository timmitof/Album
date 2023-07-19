package com.timmitof.album.domain.repository

import android.database.Observable
import com.timmitof.album.database.dao.GalleryDao
import com.timmitof.album.database.entity.Image
import io.reactivex.Flowable

class GalleryRepository(private val galleryDao: GalleryDao) {

    fun getAllImages(): Flowable<List<Image>?> = galleryDao.getAllImages()

    fun addImage(image: Image) {
        galleryDao.addImage(image)
    }

    fun deleteImage(image: Image) {
        galleryDao.deleteImage(image)
    }
}