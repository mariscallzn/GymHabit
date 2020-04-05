package com.andymariscal.gymhabit

import android.app.Application
import com.andymariscal.gymhabit.di.AppComponent
import com.andymariscal.gymhabit.di.AppModule
import com.andymariscal.gymhabit.di.DaggerAppComponent
import timber.log.Timber

class MainApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}