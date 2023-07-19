package com.timmitof.album.di

import android.content.Context
import androidx.room.Room
import com.timmitof.album.database.AppDatabase
import com.timmitof.album.domain.repository.GalleryRepository
import com.timmitof.album.database.dao.GalleryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java,"appDatabase").build()

    @Provides
    fun providesGalleryDao(galleryDatabase: AppDatabase): GalleryDao =
        galleryDatabase.galleryDao()

    @Provides
    fun providesGalleryRepository(galleryDao: GalleryDao): GalleryRepository =
        GalleryRepository(galleryDao)

}