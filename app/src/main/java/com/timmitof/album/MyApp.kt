package com.timmitof.album

import android.app.Application
import com.timmitof.album.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.fileProperties

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        stopKoin()
        startKoin {
            androidContext(this@MyApp)
            modules(AppModule.create())
        }
    }
}