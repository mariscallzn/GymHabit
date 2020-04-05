package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MuscleDao {
    @Insert
    fun insertAll(exercises: List<MuscleEntity>)

    @Query("SELECT * FROM muscles")
    fun getAll(): List<MuscleEntity>
}