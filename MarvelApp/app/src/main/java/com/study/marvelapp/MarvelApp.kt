package com.study.marvelapp

import android.app.Application
import com.study.marvelapp.di.applicationModule
import com.study.marvelapp.utils.debug
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MarvelApp : Application() {
    override fun onCreate() {
        super.onCreate()
        debug { Timber.plant( Timber.DebugTree()) }

        startKoin(this, listOf(applicationModule))
    }
}