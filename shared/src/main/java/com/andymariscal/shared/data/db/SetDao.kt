package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SetDao {

    @Insert
    fun insertAll(sets: List<SetEntity>)

    @Query("SELECT * FROM sets")
    fun getAllSets(): Flow<SetEntity>
}