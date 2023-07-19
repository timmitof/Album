package com.timmitof.album.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timmitof.album.database.dao.GalleryDao
import com.timmitof.album.database.entity.Coordinate
import com.timmitof.album.database.entity.Image

@Database(
    entities = [Image::class, Coordinate::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun galleryDao(): GalleryDao
}