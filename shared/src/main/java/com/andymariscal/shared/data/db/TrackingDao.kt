package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackingDao {

    @Insert
    fun insertAll(trackingList: List<TrackingEntity>)

    @Query("SELECT * FROM tracking")
    fun getAll(): Flow<TrackingEntity>
}