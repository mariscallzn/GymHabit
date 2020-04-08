package com.andymariscal.gymhabit.ui.assistant.operation

import com.andymariscal.model.workout.Action
import timber.log.Timber

class CreateWorkoutFactory(dependencies: OperationDependencies) : OperationFactory() {

    //TODO Hold a reference of the current item being created (ID or item itself)

    override suspend fun evaluate(action: Action, value: Any) {
        Timber.d("action = $action : value = $value")
    }

}