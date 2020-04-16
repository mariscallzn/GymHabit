package com.andymariscal.gymhabit.ui.assistant.operation

import com.andymariscal.model.workout.Action
import com.andymariscal.shared.data.db.SessionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

private const val EXERCISE = "exercises"
private const val REPS = "reps"
private const val WEIGHT = "weight"
private const val SETS = "sets"
private const val STEP_BACK = "step_back"
private const val CONTINUE = "continue"

/**
 * This class will process only the actions that need to be persisted, meaning that some action from
 * the flowSequence won't pass this Factory.
 */
class CreateWorkoutFactory(private val dependencies: OperationDependencies) : OperationFactory() {

    private val builder = CreateWorkout.Builder()

    override suspend fun evaluate(action: Action, value: Any) {
        Timber.d("action = $action : value = $value")
        when (action.metadata) {
            EXERCISE -> builder.exerciseId(value as Int)
            REPS -> builder.reps(value as Int)
            WEIGHT -> builder.amount(value as Double)
            SETS -> builder.repeatSets(value as Int)
            STEP_BACK -> saveState()
            CONTINUE -> saveState()
        }
        Timber.d("Builder = $builder")
    }

    override suspend fun execute() {

    }

    private fun saveState() {
        Timber.d("SaveState")
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

        fun amount(amount: Double): Builder = apply {
            set().amount = amount
        }

        fun restTime(rest: Int): Builder = apply {
            set().restTime = rest
        }

        fun repeatSets(times: Int): Builder = apply {
            for (x in 1..times) {
                val copySet = set().copy()
                copySet.order = x
                sets.add(copySet)
            }
        }

        fun newSet(): Builder = apply {
            sets.add(set())
            set = null
        }

        fun build(): CreateWorkout {
            val workout = CreateWorkout(workoutName, plannedDate, sets)
            workoutName = null
            plannedDate = null
            set = null
            sets.clear()
            return workout
        }

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
    var amount: Double = -1.0,
    var restTime: Int = -1 // TODO The user doesn't care
)