package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {

    @Insert
    fun insertAll(exercises: List<ExerciseEntity>)

    @Query("SELECT * FROM exercises")
    fun getAll(): List<ExerciseEntity>
}