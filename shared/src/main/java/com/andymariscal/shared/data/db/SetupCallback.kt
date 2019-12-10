package com.andymariscal.shared.data.db

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.andymariscal.shared.R
import java.util.concurrent.Executors

class SetupCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Executors.newSingleThreadExecutor().execute {
            val appDb = AppDatabase.getInstance(context)
            appDb.muscleDao().insertAll(MUSCLES)
            appDb.equipmentDao().insertAll(EQUIPMENT)
            appDb.exerciseTypeDao().insertAll(EXERCISE_TYPE)
            appDb.exerciseDao().insertAll(EXERCISES)
        }
    }

    private companion object {

        // TODO MOVE ALL IDs to constants
        const val CHEST_ID = 1

        const val MACHINE_ID = 6
        const val DUMBBELL_ID = 9

        const val STRENGTH_ID = 4

        val EXERCISES = listOf(
            ExerciseEntity(
                name = "Leverage Incline Chest Press",
                description = "TODO",
                muscleId = CHEST_ID,
                equipmentId = MACHINE_ID,
                exerciseTypeId = STRENGTH_ID
            ),
            ExerciseEntity(
                name = "Incline Dumbbell Press",
                description = "TODO",
                muscleId = CHEST_ID,
                equipmentId = DUMBBELL_ID,
                exerciseTypeId = STRENGTH_ID
            ),
            ExerciseEntity(
                name = "Low Single-Arm Crossover",
                description = "TODO",
                muscleId = CHEST_ID,
                equipmentId = DUMBBELL_ID,
                exerciseTypeId = STRENGTH_ID
            ),
            ExerciseEntity(
                name = "Dumbbell Bench Press",
                description = "TODO",
                muscleId = CHEST_ID,
                equipmentId = DUMBBELL_ID,
                exerciseTypeId = STRENGTH_ID
            )
        )

        val MUSCLES = listOf(
            MuscleEntity(CHEST_ID, "Chest", R.string.db_chest),
            MuscleEntity(2, "Forearms", R.string.db_forearms),
            MuscleEntity(3, "Lats", R.string.db_lats),
            MuscleEntity(4, "Chest", R.string.db_chest),
            MuscleEntity(5, "Middle Back", R.string.db_middle_back),
            MuscleEntity(6, "Lower Back", R.string.db_lower_back),
            MuscleEntity(7, "Neck", R.string.db_neck),
            MuscleEntity(8, "Quadriceps", R.string.db_quadriceps),
            MuscleEntity(9, "Hamstrings", R.string.db_hamstrings),
            MuscleEntity(10, "Calves", R.string.db_calves),
            MuscleEntity(11, "Triceps", R.string.db_triceps),
            MuscleEntity(12, "Traps", R.string.db_traps),
            MuscleEntity(13, "Shoulders", R.string.db_shoulders),
            MuscleEntity(14, "Abs", R.string.db_abs),
            MuscleEntity(15, "Adductors", R.string.db_adductors),
            MuscleEntity(16, "Abductors", R.string.db_abductors)
        )

        val EQUIPMENT = listOf(
            EquipmentEntity(1, "Band", R.string.db_band),
            EquipmentEntity(2, "Foam Roll", R.string.db_foam_roll),
            EquipmentEntity(3, "Barbell", R.string.db_barbell),
            EquipmentEntity(4, "Kettlebell", R.string.db_kettlebells),
            EquipmentEntity(5, "Body Only", R.string.db_body_only),
            EquipmentEntity(MACHINE_ID, "Machine", R.string.db_machine),
            EquipmentEntity(7, "Cable", R.string.db_cable),
            EquipmentEntity(8, "Medicine Ball", R.string.db_medicine_ball),
            EquipmentEntity(DUMBBELL_ID, "Dumbbell", R.string.db_dumbbell),
            EquipmentEntity(10, "None", R.string.db_none),
            EquipmentEntity(12, "E-Z Curl Bar", R.string.db_ez_curl_bar),
            EquipmentEntity(13, "Exercise Ball", R.string.db_exercise_ball)
        )

        val EXERCISE_TYPE = listOf(
            ExerciseTypeEntity(1, "Cardio", R.string.db_cardio),
            ExerciseTypeEntity(2, "Olympic Weightlifting", R.string.db_olympic_weightlifting),
            ExerciseTypeEntity(3, "Plyometrics", R.string.db_plyometrics),
            ExerciseTypeEntity(STRENGTH_ID, "Strength", R.string.db_strength),
            ExerciseTypeEntity(5, "Stretching", R.string.db_stretching),
            ExerciseTypeEntity(6, "Strongman", R.string.db_strongman)
        )
    }
}