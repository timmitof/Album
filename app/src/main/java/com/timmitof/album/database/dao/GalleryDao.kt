package com.timmitof.album.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.timmitof.album.database.entity.Image

interface GalleryDao {

    @Insert
    fun addImage(vararg image: Image)

    @Query("SELECT * FROM Image")
    fun getAllImages(): List<Image>?

    @Delete
    fun deleteImage(image: Image)
}