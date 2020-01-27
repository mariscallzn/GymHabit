package com.andymariscal.shared.data.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TrackingCommentDao {

    @Insert
    fun insertAll()
}