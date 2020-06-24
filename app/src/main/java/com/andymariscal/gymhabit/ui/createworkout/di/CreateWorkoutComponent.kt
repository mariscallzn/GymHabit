package com.andymariscal.gymhabit.ui.createworkout.di


import com.andymariscal.gymhabit.ui.createworkout.CreateWorkoutFragment
import dagger.Subcomponent

@Subcomponent(modules = [CreateWorkoutModule::class])
interface CreateWorkoutComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): CreateWorkoutComponent
    }

    fun inject(fragment: CreateWorkoutFragment)
}