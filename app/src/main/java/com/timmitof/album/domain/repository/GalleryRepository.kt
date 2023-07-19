package com.timmitof.album.domain.repository

import com.timmitof.album.database.dao.GalleryDao
import com.timmitof.album.database.entity.Image
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class GalleryRepository(private val galleryDao: GalleryDao) {

    fun getAllImages(): List<Image>? = galleryDao.getAllImages()

    fun addImage(image: Image) {
        galleryDao.addImage(image)
    }
}