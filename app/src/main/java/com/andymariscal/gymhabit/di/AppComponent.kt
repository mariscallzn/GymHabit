package com.andymariscal.gymhabit.di

import com.andymariscal.gymhabit.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}