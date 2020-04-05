package com.andymariscal.shared.data.source

import com.andymariscal.model.workout.AssistantFlowSequence
import com.andymariscal.shared.data.Result

interface AssistantDataSource {
    suspend fun createWorkoutFlowSequence(): Result<AssistantFlowSequence>
}