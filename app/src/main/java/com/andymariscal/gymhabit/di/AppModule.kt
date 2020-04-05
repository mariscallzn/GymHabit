package com.andymariscal.gymhabit.di

import android.content.Context
import com.andymariscal.shared.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

}