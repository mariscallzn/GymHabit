package com.andymariscal.shared.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andymariscal.shared.utils.Converters

@Database(
    entities = [
        TrackingEntity::class,
        ExerciseEntity::class,
        MuscleEntity::class,
        EquipmentEntity::class,
        ExerciseTypeEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun trackingDao(): TrackingDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun muscleDao(): MuscleDao

    abstract fun equipmentDao(): EquipmentDao

    abstract fun exerciseTypeDao(): ExerciseTypeDao

    abstract fun sessionCommentDao(): SessionCommentDao

    abstract fun sessionDao(): SessionDao

    abstract fun setDao(): SetDao



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