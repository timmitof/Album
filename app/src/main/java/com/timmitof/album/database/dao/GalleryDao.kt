package com.timmitof.album.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.timmitof.album.database.entity.Image
import io.reactivex.Flowable

@Dao
interface GalleryDao {

    @Insert
    fun addImage(vararg image: Image)

    @Query("SELECT * FROM Image")
    fun getAllImages(): Flowable<List<Image>?>

    @Delete
    fun deleteImage(image: Image)
}