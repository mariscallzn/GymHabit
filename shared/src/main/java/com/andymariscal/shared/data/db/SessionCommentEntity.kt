package com.andymariscal.shared.data.db

import androidx.room.*

@Entity(
    tableName = "session_comments",
    indices = [
        Index(value = ["session_id"], unique = false)
    ],
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