package com.andymariscal.gymhabit.ui.createworkout.di

import androidx.lifecycle.ViewModel
import com.andymariscal.gymhabit.ui.createworkout.CreateWorkoutViewModel
import com.andymariscal.shared.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [CreateWorkoutModule.CreateWorkoutVMModule::class])
class CreateWorkoutModule {

    @Module
    abstract class CreateWorkoutVMModule {
        @Binds
        @IntoMap
        @ViewModelKey(CreateWorkoutViewModel::class)
        abstract fun bindViewModel(viewModel: CreateWorkoutViewModel): ViewModel
    }
}