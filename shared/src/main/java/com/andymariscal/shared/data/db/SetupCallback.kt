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
            MuscleEntity(CHEST_ID, "Chest"),
            MuscleEntity(2, "Forearms"),
            MuscleEntity(3, "Lats"),
            MuscleEntity(4, "Chest"),
            MuscleEntity(5, "Middle Back"),
            MuscleEntity(6, "Lower Back"),
            MuscleEntity(7, "Neck"),
            MuscleEntity(8, "Quadriceps"),
            MuscleEntity(9, "Hamstrings"),
            MuscleEntity(10, "Calves"),
            MuscleEntity(11, "Triceps"),
            MuscleEntity(12, "Traps"),
            MuscleEntity(13, "Shoulders"),
            MuscleEntity(14, "Abs"),
            MuscleEntity(15, "Adductors"),
            MuscleEntity(16, "Abductors")
        )

        val EQUIPMENT = listOf(
            EquipmentEntity(1, "Band"),
            EquipmentEntity(2, "Foam Roll"),
            EquipmentEntity(3, "Barbell"),
            EquipmentEntity(4, "Kettlebell"),
            EquipmentEntity(5, "Body Only"),
            EquipmentEntity(MACHINE_ID, "Machine"),
            EquipmentEntity(7, "Cable"),
            EquipmentEntity(8, "Medicine Ball"),
            EquipmentEntity(DUMBBELL_ID, "Dumbbell"),
            EquipmentEntity(10, "None"),
            EquipmentEntity(12, "E-Z Curl Bar"),
            EquipmentEntity(13, "Exercise Ball")
        )

        val EXERCISE_TYPE = listOf(
            ExerciseTypeEntity(1, "Cardio"),
            ExerciseTypeEntity(2, "Olympic Weightlifting"),
            ExerciseTypeEntity(3, "Plyometrics"),
            ExerciseTypeEntity(STRENGTH_ID, "Strength"),
            ExerciseTypeEntity(5, "Stretching"),
            ExerciseTypeEntity(6, "Strongman")
        )
    }
}
