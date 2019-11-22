package com.andymariscal.gymhabit.di

import android.content.Context
import com.andymariscal.gymhabit.MainApplication
import com.andymariscal.shared.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: MainApplication) {

    @Provides
    fun provideContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)
}