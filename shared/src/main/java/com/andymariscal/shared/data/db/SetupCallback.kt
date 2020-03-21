package com.andymariscal.shared.data.db

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.andymariscal.model.*
import com.andymariscal.shared.inf.IConverter
import com.andymariscal.shared.utils.IOUtils
import com.squareup.moshi.Moshi
import timber.log.Timber
import java.util.concurrent.Executors

class SetupCallback(private val context: Context) : RoomDatabase.Callback() {

    //region RoomDatabase.Callback
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        Timber.i("onCreate")
        Executors.newSingleThreadExecutor().execute {

            val dbDataString = IOUtils.convertToString(
                context.assets.open("database/db_data.json")
            )

            dbDataString?.let {
                Moshi.Builder().build()
                    .adapter(PrePopulateData::class.java).fromJson(dbDataString)?.run {
                        val appDb = AppDatabase.getInstance(context)
                        appDb.muscleDao().insertAll(musclesConverter.convertList(muscles))
                        appDb.equipmentDao().insertAll(equipmentsConverter.convertList(equipments))
                        appDb.exerciseTypeDao().insertAll(exerciseTypesConverter.convertList(exerciseTypes))
                        appDb.exerciseDao().insertAll(exercisesConverter.convertList(exercises))
                    }
            } ?: Timber.e("Pre-populate data cannot be parsed")

        }
    }
    //endregion

    //region Converters
    private val musclesConverter = object : IConverter<Muscles, MuscleEntity> {
        override fun convert(source: Muscles): MuscleEntity =
            MuscleEntity(
                source.uid,
                source.name
            )
    }

    private val equipmentsConverter = object : IConverter<Equipments, EquipmentEntity> {
        override fun convert(source: Equipments): EquipmentEntity =
            EquipmentEntity(
                source.uid,
                source.name
            )
    }

    private val exerciseTypesConverter = object : IConverter<ExerciseTypes, ExerciseTypeEntity> {
        override fun convert(source: ExerciseTypes): ExerciseTypeEntity =
            ExerciseTypeEntity(
                source.uid,
                source.name
            )
    }

    private val exercisesConverter = object : IConverter<Exercises, ExerciseEntity> {
        override fun convert(source: Exercises): ExerciseEntity =
            ExerciseEntity(
                0,
                source.name,
                source.description,
                source.muscleId,
                source.equipmentId,
                source.exerciseTypeId
            )
    }
    //endregion
}
