package com.andymariscal.gymhabit.ui.assistant.domain

import com.andymariscal.shared.data.workout.WorkoutRepository

class CreateWorkoutUseCase(private val repository: WorkoutRepository) {
    suspend fun letsSee(): Dummy = Dummy(repository.testCourutines())
}

data class Dummy (val state: String)