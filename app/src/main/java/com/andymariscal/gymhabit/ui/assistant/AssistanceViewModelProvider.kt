package com.andymariscal.gymhabit.ui.assistant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andymariscal.gymhabit.ui.assistant.domain.CreateWorkoutUseCase
import com.andymariscal.shared.data.workout.DefaultWorkoutRepository

class AssistanceViewModelProvider : ViewModelProvider.NewInstanceFactory() {

    //region ViewModelProvider.NewInstanceFactory
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            CreateWorkoutUseCase::class.java
        ).newInstance(
            CreateWorkoutUseCase(DefaultWorkoutRepository()))
    }
    //endregion
}