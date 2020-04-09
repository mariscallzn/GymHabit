package com.andymariscal.gymhabit.ui.assistant.operation

import com.andymariscal.model.workout.Action
import timber.log.Timber

private const val WEIGHT = "weight"

class CreateWorkoutFactory(dependencies: OperationDependencies) : OperationFactory() {

    private val builder = CreateWorkout.Builder()

    override suspend fun evaluate(action: Action, value: Any) {
        Timber.d("action = $action : value = $value")
        when(action.metadata) {
            WEIGHT -> builder.amount(value as Int)
        }
    }

    override suspend fun execute() {

    }

}

private class CreateWorkout private constructor(
    val workoutName: String? = null,
    val plannedDate: String? = null,
    val sets: MutableList<Set> = mutableListOf()
) {
    data class Builder(
        //SessionEntity properties
        private var workoutName: String? = null,
        private var plannedDate: String? = null,

        //SetEntity properties
        private var set: Set? = null,
        private val sets: MutableList<Set> = mutableListOf()
    ) {

        fun workoutTitle(title: String): Builder = apply {
            workoutName = title
        }

        fun plannedDate(date: String): Builder = apply {
            plannedDate = date
        }

        fun setType(setType: Int): Builder = apply {
            set().setType = setType
        }

        fun exerciseId(exerciseId: Int): Builder = apply {
            set().exerciseId = exerciseId
        }

        fun setOrder(order: Int): Builder = apply {
            set().order = order
        }

        fun repetitionSpeed(speed: Int): Builder = apply {
            set().repetitionSpeed = speed
        }

        fun reps(reps: Int): Builder = apply {
            set().reps = reps
        }

        fun amount(amount: Int): Builder = apply {
            set().amount = amount
        }

        fun restTime(rest: Int): Builder = apply {
            set().restTime = rest
        }

        fun newSet(): Builder = apply {
            sets.add(set())
            set = null
        }

        fun build() = CreateWorkout(workoutName, plannedDate, sets)

        private fun set(): Set {
            if (set == null) {
                set = Set()
            }
            return set!!
        }
    }
}

private data class Set(
    var setType: Int = -1,
    var exerciseId: Int = -1,
    var order: Int = -1,
    var repetitionSpeed: Int = 1,
    var reps: Int = -1,
    var amount: Int = -1,
    var restTime: Int = -1
)