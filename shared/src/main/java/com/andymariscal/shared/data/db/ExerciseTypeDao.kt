package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseTypeDao {
    @Insert
    fun insertAll(exercises: List<ExerciseTypeEntity>)

    @Query("SELECT * FROM exercise_types")
    fun getAll(): Flow<ExerciseTypeEntity>
}