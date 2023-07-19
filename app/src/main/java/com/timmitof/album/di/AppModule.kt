package com.timmitof.album.di

import androidx.room.Room
import com.timmitof.album.database.AppDatabase
import com.timmitof.album.database.dao.GalleryDao
import com.timmitof.album.domain.repository.GalleryRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule : InjectionModule {

    override fun create(): Module = module {
        single {
            Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "appDatabase"
            ).allowMainThreadQueries().build()
        }
        single<GalleryDao> {
            val database = get<AppDatabase>()
            database.galleryDao()
        }
        single { GalleryRepository(get()) }
    }

}