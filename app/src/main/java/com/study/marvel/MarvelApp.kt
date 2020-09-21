package com.study.marvel

import android.app.Application
import com.study.marvel.di.applicationModule
import com.study.marvel.utils.debug
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MarvelApp : Application() {
    override fun onCreate() {
        super.onCreate()
        debug { Timber.plant( Timber.DebugTree()) }

        startKoin(this, listOf(applicationModule))
    }
}