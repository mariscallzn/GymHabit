package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDao {

    @Insert
    fun insertAll(sessions: List<SessionEntity>)

    @Query("SELECT * FROM sessions")
    fun getAllSessions(): Flow<SessionEntity>
}