package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EquipmentDao {
    @Insert
    fun insertAll(exercises: List<EquipmentEntity>)

    @Query("SELECT * FROM equipments")
    fun getAll(): Flow<EquipmentEntity>
}