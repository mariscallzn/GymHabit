package com.andymariscal.gymhabit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //Timber setup
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}