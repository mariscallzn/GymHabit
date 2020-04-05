package com.andymariscal.shared.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelBuilderModule {

    @Binds
    abstract fun bindViewModelFactory(
        factory: GymhabitViewModelFactory
    ): ViewModelProvider.Factory
}