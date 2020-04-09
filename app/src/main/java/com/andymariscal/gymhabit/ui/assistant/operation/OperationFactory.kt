package com.andymariscal.gymhabit.ui.assistant.operation

import com.andymariscal.model.workout.Action
import com.andymariscal.shared.data.db.AppDatabase

data class OperationDependencies(
    var flowSequenceId: String,
    val appDatabase: AppDatabase
)

private const val CREATE_WORKOUT_FLOW_SEQUENCE = "create_workout"

abstract class OperationFactory {

    companion object {
        private var createWorkoutOperation: CreateWorkoutFactory? = null

        fun getFactory(dependencies: OperationDependencies): OperationFactory =
            when (dependencies.flowSequenceId) {
                CREATE_WORKOUT_FLOW_SEQUENCE -> {
                    if (createWorkoutOperation == null) {
                        createWorkoutOperation = CreateWorkoutFactory(dependencies)
                    }
                    createWorkoutOperation!!
                }
                else -> {
                    TODO()
                }
            }
    }

    abstract suspend fun evaluate(action: Action, value: Any)
    abstract suspend fun execute()
}