package com.andymariscal.shared.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.andymariscal.shared.BuildConfig
import com.andymariscal.shared.utils.Converters

@Database(
    exportSchema = true,
    entities = [
        TrackingEntity::class,
        SessionEntity::class,
        SessionCommentEntity::class,
        SetEntity::class,
        SetTypeEntity::class,
        ExerciseEntity::class,
        MuscleEntity::class,
        EquipmentEntity::class,
        ExerciseTypeEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    //region Data Access Objects
    abstract fun trackingDao(): TrackingDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun muscleDao(): MuscleDao

    abstract fun equipmentDao(): EquipmentDao

    abstract fun exerciseTypeDao(): ExerciseTypeDao

    abstract fun sessionCommentDao(): SessionCommentDao

    abstract fun sessionDao(): SessionDao

    abstract fun setDao(): SetDao
    //endregion

    //region Initializer
    companion object {
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
                    BuildConfig.DB_NAME
                )
                .addCallback(SetupCallback(context))
                .build()
    }
    //endregion
}