package com.andymariscal.shared.domain

import com.andymariscal.model.workout.FlowSequence
import com.andymariscal.shared.data.Result
import com.andymariscal.shared.data.flowsequence.FlowSequenceRepository
import com.andymariscal.shared.inf.DSFlowSequence
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

typealias FlowSequenceId = String

class LoadFlowSequenceUseCase @Inject constructor(
    private val repository: FlowSequenceRepository,
    ioDispatcher: CoroutineDispatcher
) : UseCase<String, LoadFlowSequenceResult>(ioDispatcher) {

    override suspend fun execute(parameters: FlowSequenceId): LoadFlowSequenceResult {
        //We could return a default error sequence when Result.Error or not succeed
        // With actions like "Try Again" or point them to a different direction
        val flowSequence = when (val result = repository.loadFlowSequenceById(parameters)) {
            is Result.Success -> {
                val fs = DSFlowSequence<FlowSequence>()
                result.data.flowSequence.forEach {
                    // Create an Object/Interface which holds the message and the List of actions
                    fs followBy it trackStep it.step
                }
                fs
            }
            is Result.Error -> {
                TODO()
            }
            else -> {
                TODO()
            }
        }
        return LoadFlowSequenceResult(flowSequence)
    }
}

data class LoadFlowSequenceResult(val dsFlowSequence: DSFlowSequence<FlowSequence>)