package com.andymariscal.shared.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "session_comments",
    foreignKeys = [
        ForeignKey(
            entity = SessionEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("session_id")
        )
    ]
)
data class SessionCommentEntity(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,
    @ColumnInfo(name = "session_id")
    val sessionId: Int,
    @ColumnInfo(name = "comment")
    val comment: String
)