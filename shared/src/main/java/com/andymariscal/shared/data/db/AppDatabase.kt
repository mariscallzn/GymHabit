package com.andymariscal.shared.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        private const val DATABASE_NAME = "gymhabit-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .addCallback(SetupCallback(context))
                .build()
    }
}