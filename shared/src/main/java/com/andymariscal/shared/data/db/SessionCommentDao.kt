package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionCommentDao {

    @Insert
    fun insertAll(comments: List<SessionCommentEntity>)

    @Query("SELECT * FROM session_comments")
    fun getSessionComments(): Flow<SessionCommentEntity>
}