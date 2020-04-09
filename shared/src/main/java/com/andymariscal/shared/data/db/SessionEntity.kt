package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "created_time", defaultValue = "CURRENT_TIMESTAMP")
    val createdTime: String,
    @ColumnInfo(name = "planned_time")
    val plannedTime: String,
    @ColumnInfo(name = "completed_time")
    val completedTime: String
)