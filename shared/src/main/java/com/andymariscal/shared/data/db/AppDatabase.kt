package com.andymariscal.shared.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ExerciseEntity::class,
        MuscleEntity::class,
        EquipmentEntity::class,
        ExerciseTypeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    abstract fun muscleDao(): MuscleDao

    abstract fun equipmentDao(): EquipmentDao

    abstract fun exerciseTypeDao(): ExerciseTypeDao
}