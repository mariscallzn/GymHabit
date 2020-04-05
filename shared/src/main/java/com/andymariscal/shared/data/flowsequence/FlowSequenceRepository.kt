package com.andymariscal.shared.data.flowsequence

import com.andymariscal.model.workout.AssistantFlowSequence
import com.andymariscal.shared.data.Result
import com.andymariscal.shared.data.source.AssistantDataSource

interface FlowSequenceRepository {
    suspend fun loadFlowSequenceById(flowSequenceId: String): Result<AssistantFlowSequence>
}

class DefaultFlowSequenceRepository(
    private val localDataSource: AssistantDataSource
) : FlowSequenceRepository {

    override suspend fun loadFlowSequenceById(flowSequenceId: String): Result<AssistantFlowSequence> =
        localDataSource.createWorkoutFlowSequence()

}